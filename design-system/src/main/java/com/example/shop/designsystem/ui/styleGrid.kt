package com.example.shop.designsystem.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import com.example.shop.designsystem.ui.item.StyleItem
import com.example.shop.domain.model.ContentsItemType

fun LazyGridScope.styleGrid(
  items: List<ContentsItemType.Style>
) {
  items.firstOrNull()?.let { firstStyle ->
    item(span = { GridItemSpan(2) }) {
      StyleItem(
        style = firstStyle
      )
    }
  }
  item {
    Column {
      items.getOrNull(1)?.let {
        StyleItem(
          style = it
        )
      }
      items.getOrNull(2)?.let {
        StyleItem(
          style = it
        )
      }
    }
  }
  if (items.size > 3) {
    itemsIndexed(
      items = items.drop(3),
      key = { _, style -> style.linkUrl },
    ) { idx, item ->
      StyleItem(
        style = item
      )
    }
  }
}