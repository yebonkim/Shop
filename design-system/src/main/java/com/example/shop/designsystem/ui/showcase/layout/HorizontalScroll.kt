package com.example.shop.designsystem.ui.showcase.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.showcase.item.GoodsItem
import com.example.shop.domain.model.ContentsItemType
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HorizontalScroll(
  modifier: Modifier = Modifier,
  goodsList: ImmutableList<ContentsItemType.Goods>,
  onClickItem: (ContentsItemType.Goods) -> Unit = {},
) {
  LazyRow(
    modifier = modifier.fillMaxWidth(),
    contentPadding = PaddingValues(12.dp),
  ) {
    item(
      key = "emptyItem",
      contentType = { "emptyItem" },
    ) {
      // To prevent unintended scrolling when the first item changes
    }

    items(
      items = goodsList,
      key = { goods -> goods.linkUrl },
      contentType = { _ -> "goods" }
    ) { goods ->
      GoodsItem(
        modifier = Modifier.padding(horizontal = 2.dp),
        goods = goods,
        onClick = { onClickItem(goods) },
      )
    }
  }
}