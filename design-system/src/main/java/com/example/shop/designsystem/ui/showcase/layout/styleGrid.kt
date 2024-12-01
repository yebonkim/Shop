package com.example.shop.designsystem.ui.showcase.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import com.example.shop.designsystem.ui.showcase.item.StyleItem
import com.example.shop.domain.model.ContentsItemType

fun LazyGridScope.styleGrid(
  styles: List<ContentsItemType.Style>,
  onClickStyle: (ContentsItemType.Style) -> Unit,
) {
  HighlightGrid(
    styles = styles.take(3),
    onClickStyle = onClickStyle,
  )

  items(
    items = styles.drop(3),
    key = { style -> style.linkUrl },
    contentType = { _ -> "style" },
  ) { style ->
    StyleItem(
      style = style,
      onClick = { onClickStyle(style) },
    )
  }
}

private fun LazyGridScope.HighlightGrid(
  styles: List<ContentsItemType.Style>,
  onClickStyle: (ContentsItemType.Style) -> Unit,
) {
  styles.firstOrNull()?.let { style ->
    item(
      span = { GridItemSpan(2) },
      contentType = { "HighlightStyleItem" },
    ) {
      StyleItem(
        style = style,
        onClick = { onClickStyle(style) },
      )
    }
  }

  item(
    span = { GridItemSpan(1) },
    contentType = { "ColumnedStyleItem" },
  ) {
    Column {
      styles.drop(1).take(2).forEach { style ->
        StyleItem(
          style = style,
          onClick = { onClickStyle(style) },
        )
      }
    }
  }
}