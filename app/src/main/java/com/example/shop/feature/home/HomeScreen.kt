package com.example.shop.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.mvrx.compose.mavericksViewModel
import com.airbnb.mvrx.withState
import com.example.shop.designsystem.ui.ShowcaseList

@Composable
fun HomeScreen() {
  val viewModel: HomeViewModel = mavericksViewModel()
  val state by viewModel.stateFlow.collectAsStateWithLifecycle(withState(viewModel) { it })
  val showcases = remember(state.showcases) { state.showcases() }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .statusBarsPadding()
  ) { padding ->
    ShowcaseList(
      modifier = Modifier.padding(padding),
      showcases = showcases ?: emptyList(),
      onClickHeaderLink = { },
      onClickFooter = { }
    )
  }
}