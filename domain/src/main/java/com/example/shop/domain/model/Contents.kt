package com.example.shop.domain.model

sealed interface Contents {
  data class BannerContents(
    val list: List<Banner>
  ) : Contents

  data class GridContents(
    val list: List<Goods>
  ) : Contents

  data class ScrollContents(
    val list: List<Goods>
  ) : Contents

  data class StyleContents(
    val list: List<Style>
  ) : Contents
}