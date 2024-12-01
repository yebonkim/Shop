package com.example.shop.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.compose.mavericksViewModel
import com.airbnb.mvrx.withState
import com.example.shop.designsystem.ui.showcase.ShowcaseList
import com.example.shop.designsystem.ui.component.LoadingProgressIndicator
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun HomeScreen(
  navigateToDetail: (String) -> Unit
) {
  val viewModel: HomeViewModel = mavericksViewModel()
  val state by viewModel.stateFlow.collectAsStateWithLifecycle(withState(viewModel) { it })
  val showcases = remember(state.showcases) { state.showcases() }
  val lifecycleOwner = LocalLifecycleOwner.current

  LaunchedEffect(Unit) {
    viewModel.uiEffect.receiveAsFlow()
      .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.RESUMED)
      .collect { effect ->
        when (effect) {
          is HomeUiEffect.NavigateToDetail -> navigateToDetail(effect.url)
        }
      }
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
  ) { padding ->
    when {
      showcases != null -> {
        ShowcaseList(
          modifier = Modifier.padding(padding),
          showcases = showcases,
          onClickLink = { link -> viewModel.onClickLink(link) },
          onClickFooter = { showcaseId -> viewModel.onClickFooter(showcaseId) }
        )
      }
      state.showcases is Loading -> {
        LoadingProgressIndicator(modifier = Modifier.padding(padding))
      }
    }
  }
}