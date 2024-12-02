package com.example.shop.designsystem.ui.showcase.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.showcase.item.GoodsItem
import com.example.shop.domain.model.ContentsItemType
import kotlinx.collections.immutable.ImmutableList

fun LazyGridScope.goodsGrid(
  goodsList: ImmutableList<ContentsItemType.Goods>,
  onClickGoods: (ContentsItemType.Goods) -> Unit = {},
) {
  items(
    items = goodsList,
    key = { goods -> goods.linkUrl },
    contentType = { goods -> "goods" }
  ) { goods ->
    GoodsItem(
      modifier = Modifier.padding(2.dp),
      goods = goods,
      onClick = { onClickGoods(goods) },
    )
  }
}