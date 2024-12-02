package com.example.shop.designsystem.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AsyncImage(
  imageUrl: String,
  contentDescription: String?,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Crop,
  colorFilter: ColorFilter? = null,
) {
  GlideImage(
    model = imageUrl,
    contentDescription = contentDescription,
    modifier = modifier,
    contentScale = contentScale,
    loading = placeholder(painter = ColorPainter(MaterialTheme.colorScheme.surface)),
    transition = CrossFade,
    colorFilter = colorFilter
  )
}