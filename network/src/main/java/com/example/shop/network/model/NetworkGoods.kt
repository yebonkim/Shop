package com.example.shop.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGoods(
  val brandName: String = "",
  val price: Int = 0,
  val saleRate: Int = 0,
  val hasCoupon: Boolean = false,
  @SerialName("linkURL")
  val linkUrl: String = "",
  @SerialName("thumbnailURL")
  val thumbnailUrl: String = ""
)
