package com.example.shop.designsystem.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.shop.designsystem.R

@Composable
fun UpdateWarning() {
  Text(
    text = stringResource(id = R.string.request_update),
    modifier = Modifier.fillMaxWidth(),
    textAlign = TextAlign.Center,
    style = MaterialTheme.typography.bodyLarge,
  )
}