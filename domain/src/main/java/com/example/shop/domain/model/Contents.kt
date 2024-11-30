package com.example.shop.domain.model

sealed interface Contents {
  data class BannerContents(
    val items: List<ContentsItemType.Banner>
  ) : Contents

  data class GridContents(
    val items: List<ContentsItemType.Goods>,
  ) : Contents

  data class ScrollContents(
    val items: List<ContentsItemType.Goods>
  ) : Contents

  data class StyleContents(
    val items: List<ContentsItemType.Style>
  ) : Contents

  data class Unknown(
    val items: List<ContentsItemType>
  ) : Contents
}