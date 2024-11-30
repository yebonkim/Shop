package com.example.shop.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkHeader(
  val title: String = "",
  @SerialName("iconURL")
  val iconUrl: String? = null,
  @SerialName("linkURL")
  val linkUrl: String? = null
)
