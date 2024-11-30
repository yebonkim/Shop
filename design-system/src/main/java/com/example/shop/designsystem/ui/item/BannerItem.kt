package com.example.shop.designsystem.ui.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.theme.ShopTheme
import com.example.shop.designsystem.ui.component.AsyncImage
import com.example.shop.domain.model.ContentsItemType

@Composable
fun BannerItem(
  modifier: Modifier = Modifier,
  banner: ContentsItemType.Banner,
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .aspectRatio(1f)
  ) {
    AsyncImage(
      model = banner.thumbnailUrl,
      contentDescription = banner.title,
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Fit
    )
    Column(
      modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
      Text(
        text = banner.keyword,
        color = Color.White,
        style = MaterialTheme.typography.labelMedium,
      )
      Spacer(modifier = Modifier.weight(1f))
      Text(
        text = banner.title,
        modifier = Modifier.padding(bottom = 8.dp),
        color = Color.White,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
      )
      Text(
        text = banner.description,
        color = Color.White,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }
  }
}

@Composable
@Preview
fun BannerPreview() {
  ShopTheme {
    BannerItem(
      banner = ContentsItemType.Banner(
        title = "Goods",
        description = "Goods",
        keyword = "Goods",
        linkUrl = "",
        thumbnailUrl = "",
      )
    )
  }
}