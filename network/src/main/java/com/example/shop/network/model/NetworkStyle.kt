package com.example.shop.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkStyle(
  @SerialName("linkURL")
  val linkUrl: String,
  @SerialName("thumbnailURL")
  val thumbnailUrl: String
)
