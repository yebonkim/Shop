package com.example.shop.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NetworkFooter {

  @Serializable
  @SerialName("MORE")
  data class MoreFooter(
    val title: String,
    @SerialName("iconURL")
    val iconUrl: String? = null,
  ): NetworkFooter

  @Serializable
  @SerialName("REFRESH")
  data class RefreshFooter(
    val title: String,
    @SerialName("iconURL")
    val iconUrl: String? = null,
  ): NetworkFooter
}