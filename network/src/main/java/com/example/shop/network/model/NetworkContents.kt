package com.example.shop.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NetworkContents {

  @Serializable
  @SerialName("BANNER")
  data class BannerContents(
    val banners: List<NetworkBanner>
  ): NetworkContents

  @Serializable
  @SerialName("GRID")
  data class GridContents(
    val goods: List<NetworkGoods>
  ): NetworkContents

  @Serializable
  @SerialName("SCROLL")
  data class ScrollContents(
    val goods: List<NetworkGoods>
  ): NetworkContents

  @Serializable
  @SerialName("STYLE")
  data class StyleContents(
    val styles: List<NetworkStyle>
  ): NetworkContents
}