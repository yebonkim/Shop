package com.example.shop.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkBanner(
  val title: String,
  val description: String,
  val keyword: String,
  @SerialName("linkURL")
  val linkUrl: String,
  @SerialName("thumbnailURL")
  val thumbnailUrl: String
)
