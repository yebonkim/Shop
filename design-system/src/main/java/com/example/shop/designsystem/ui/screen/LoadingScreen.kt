package com.example.shop.designsystem.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(
  modifier: Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .padding(12.dp),
    contentAlignment = Alignment.TopCenter,
  ) {
    CircularProgressIndicator()
  }
}