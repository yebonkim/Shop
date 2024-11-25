package com.example.shop.domain.model

data class Goods(
  val brandName: String,
  val price: Int,
  val saleRate: Int,
  val hasCoupon: Boolean,
  val linkUrl: String,
  val thumbnailUrl: String
)
