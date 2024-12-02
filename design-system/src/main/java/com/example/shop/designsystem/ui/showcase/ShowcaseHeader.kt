package com.example.shop.designsystem.ui.showcase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.R
import com.example.shop.designsystem.theme.ShopTheme
import com.example.shop.designsystem.ui.component.AsyncImage
import com.example.shop.domain.model.Header

@Composable
fun ShowcaseHeader(
  modifier: Modifier = Modifier,
  header: Header,
  onClickLink: ((String) -> Unit)? = null,
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 12.dp, vertical = 20.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Row(
      modifier = Modifier
        .weight(1f, fill = false)
        .padding(end = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = header.title,
        modifier = Modifier
          .weight(1f, fill = false)
          .padding(end = 8.dp),
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = 2,
        style = MaterialTheme.typography.titleLarge,
      )
      header.iconUrl?.let { iconUrl ->
        AsyncImage(
          imageUrl = iconUrl,
          contentDescription = "Header icon",
          modifier = Modifier.size(20.dp),
        )
      }
    }
    header.linkUrl?.let { linkUrl ->
      Text(
        text = stringResource(R.string.more),
        modifier = Modifier
          .align(Alignment.Bottom)
          .clickable { onClickLink?.invoke(linkUrl) },
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        textDecoration = TextDecoration.Underline,
        style = MaterialTheme.typography.labelMedium,
      )
    }
  }
}

@Composable
@Preview
fun ShowcaseHeaderPreview() {
  ShopTheme {
    ShowcaseHeader(
      header = Header(
        title = "Header TitleHeader TitleHeader TitleHeader Title",
        iconUrl = "https://image.msscdn.net/icons/mobile/clock.png",
        linkUrl = "https://www.example.com"
      )
    )
  }
}