package com.example.shop.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGoods(
  val brandName: String,
  val price: Int,
  val saleRate: Int,
  val hasCoupon: Boolean,
  @SerialName("linkURL")
  val linkUrl: String,
  @SerialName("thumbnailURL")
  val thumbnailUrl: String
)
