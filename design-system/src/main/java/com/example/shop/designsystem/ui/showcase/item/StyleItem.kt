package com.example.shop.designsystem.ui.showcase.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.component.AsyncImage
import com.example.shop.domain.model.ContentsItemType

@Composable
fun StyleItem(
  modifier: Modifier = Modifier,
  style: ContentsItemType.Style,
  onClick: () -> Unit = {},
) {
  AsyncImage(
    model = style.thumbnailUrl,
    contentDescription = null,
    modifier = modifier
      .aspectRatio(0.8f)
      .padding(2.dp)
      .clickable { onClick() },
    contentScale = ContentScale.Crop
  )
}