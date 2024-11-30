package com.example.shop.domain.model

sealed interface ContentsItemType {
  data class Banner(
    val title: String,
    val description: String,
    val keyword: String,
    val linkUrl: String,
    val thumbnailUrl: String
  ): ContentsItemType

  data class Goods(
    val brandName: String,
    val price: Int,
    val saleRate: Int,
    val hasCoupon: Boolean,
    val linkUrl: String,
    val thumbnailUrl: String
  ): ContentsItemType

  data class Style(
    val linkUrl: String,
    val thumbnailUrl: String
  ): ContentsItemType
}