package com.example.shop.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkShowcaseRespond(
  val data: List<NetworkShowcase> = emptyList()
)