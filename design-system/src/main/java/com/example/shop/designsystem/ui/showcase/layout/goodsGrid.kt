package com.example.shop.designsystem.ui.showcase.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.showcase.item.GoodsItem
import com.example.shop.domain.model.ContentsItemType

fun LazyGridScope.goodsGrid(
  goodsList: List<ContentsItemType.Goods>,
  onClickGoods: (ContentsItemType.Goods) -> Unit = {},
) {
  items(
    items = goodsList,
    key = { goods -> goods.linkUrl },
    contentType = { goods -> goods::class.java.simpleName }
  ) { goods ->
    GoodsItem(
      modifier = Modifier.padding(2.dp),
      goods = goods,
      onClick = { onClickGoods(goods) },
    )
  }
}