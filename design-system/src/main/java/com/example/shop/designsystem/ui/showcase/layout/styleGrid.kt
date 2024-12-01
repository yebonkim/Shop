package com.example.shop.designsystem.ui.showcase.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.showcase.item.StyleItem
import com.example.shop.domain.model.ContentsItemType

fun LazyGridScope.styleGrid(
  items: List<ContentsItemType.Style>,
  onClickItem: (ContentsItemType.Style) -> Unit,
) {
  val modifier = Modifier
    .aspectRatio(0.8f)
    .padding(2.dp)

  HighlightGrid(
    items = items.take(3),
    itemModifier = modifier,
    onClickItem = onClickItem,
  )

  itemsIndexed(
    items = items.drop(3),
    key = { _, style -> style.linkUrl },
    contentType = { _, _ -> "StyleItem" },
  ) { idx, item ->
    StyleItem(
      style = item,
      modifier = modifier,
      onClick = { onClickItem(item) },
    )
  }
}

private fun LazyGridScope.HighlightGrid(
  items: List<ContentsItemType.Style>,
  itemModifier: Modifier,
  onClickItem: (ContentsItemType.Style) -> Unit,
) {
  items.firstOrNull()?.let { style ->
    item(
      span = { GridItemSpan(2) },
    ) {
      StyleItem(
        style = style,
        modifier = itemModifier,
        onClick = { onClickItem(style) },
      )
    }
  }

  item(
    span = { GridItemSpan(1) },
  ) {
    Column {
      items.drop(1).take(2).forEach { style ->
        StyleItem(
          style = style,
          modifier = itemModifier,
          onClick = { onClickItem(style) },
        )
      }
    }
  }
}