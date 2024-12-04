package com.example.shop.designsystem.ui.showcase.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import com.example.shop.designsystem.ui.showcase.item.StyleItem
import com.example.shop.domain.model.ContentsItemType
import kotlinx.collections.immutable.ImmutableList

fun LazyGridScope.styleGrid(
  styles: ImmutableList<ContentsItemType.Style>,
  navigateToDetail: (String) -> Unit,
) {
  HighlightGrid(
    styles = styles.take(3),
    navigateToDetail = navigateToDetail,
  )

  items(
    items = styles.drop(3),
    key = { style -> style.linkUrl },
    contentType = { _ -> "style" },
  ) { style ->
    StyleItem(
      style = style,
      onClick = { navigateToDetail(style.linkUrl) },
    )
  }
}

private fun LazyGridScope.HighlightGrid(
  styles: List<ContentsItemType.Style>,
  navigateToDetail: (String) -> Unit,
) {
  styles.firstOrNull()?.let { style ->
    item(
      span = { GridItemSpan(2) },
      key = style.linkUrl,
      contentType = { "HighlightStyleItem" },
    ) {
      StyleItem(
        style = style,
        onClick = { navigateToDetail(style.linkUrl) },
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
          onClick = { navigateToDetail(style.linkUrl) },
        )
      }
    }
  }
}