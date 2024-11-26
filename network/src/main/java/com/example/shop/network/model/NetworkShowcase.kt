package com.example.shop.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkShowcase(
  val header: NetworkHeader? = null,
  val contents: NetworkContents,
  val footer: NetworkFooter? = null
)