package com.example.shop.designsystem.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.item.GoodsItem
import com.example.shop.domain.model.ContentsItemType

@Composable
fun HorizontalScroll(
  modifier: Modifier = Modifier,
  items: List<ContentsItemType.Goods>
) {
  LazyRow(
    modifier = modifier.fillMaxWidth(),
    contentPadding = PaddingValues(12.dp),
  ) {
    items(
      items = items,
      key = { item -> item.linkUrl },
    ) { item ->
      GoodsItem(
        modifier = Modifier.padding(horizontal = 2.dp),
        goods = item
      )
    }
  }
}