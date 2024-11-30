package com.example.shop.designsystem.ui.showcase.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.shop.designsystem.ui.component.AsyncImage
import com.example.shop.domain.model.ContentsItemType

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StyleItem(
  modifier: Modifier = Modifier,
  style: ContentsItemType.Style
) {
  AsyncImage(
    model = style.thumbnailUrl,
    contentDescription = null,
    modifier = modifier,
    contentScale = ContentScale.Crop
  )
}