package com.example.shop.designsystem.ui.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.theme.ShopTheme
import com.example.shop.designsystem.ui.component.AsyncImage
import com.example.shop.domain.model.Footer
import com.example.shop.domain.model.FooterType

@Composable
fun ShowcaseFooter(
  modifier: Modifier = Modifier,
  footer: Footer,
  onClick: () -> Unit,
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 12.dp, vertical = 20.dp)
      .background(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(12.dp)
      )
      .clickable { onClick() }
      .padding(12.dp),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    footer.iconUrl?.let { iconUrl ->
      AsyncImage(
        imageUrl = iconUrl,
        contentDescription = "Footer icon",
        modifier = Modifier.size(20.dp),
        colorFilter = ColorFilter.tint(
          color = MaterialTheme.colorScheme.onPrimary
        )
      )
    }
    Text(
      text = footer.title,
      modifier = Modifier
        .weight(1f, fill = false)
        .padding(start = 4.dp),
      color = MaterialTheme.colorScheme.onPrimary,
      textAlign = TextAlign.Center,
      overflow = TextOverflow.Ellipsis,
      maxLines = 1,
      style = MaterialTheme.typography.bodyLarge,
    )
  }
}

@Composable
@Preview
fun ShowcaseFooterPreview() {
  ShopTheme {
    ShowcaseFooter(
      footer = Footer(
        type = FooterType.MORE,
        title = "Header TitleHeader TitleHeader TitleHeader Title",
        iconUrl = "",
      ),
      onClick = {}
    )
  }
}