package com.example.shop.designsystem.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorPage(
  modifier: Modifier,
  onClickRetry: () -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Error")
    Button(onClick = onClickRetry) {
      Text("Refresh")
    }
  }
}