package com.example.shop.designsystem.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.item.GoodsItem
import com.example.shop.domain.model.ContentsItemType

fun LazyGridScope.grid(
  items: List<ContentsItemType.Goods>
) {
  this.items(
    items = items,
    key = { item -> item.linkUrl },
    contentType = { item -> item::class.java.simpleName }
  ) { items ->
    GoodsItem(
      modifier = Modifier.padding(2.dp),
      goods = items
    )
  }
}