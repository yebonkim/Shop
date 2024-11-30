package com.example.shop.network.model

import com.example.shop.network.serializer.ContentsSerializer
import kotlinx.serialization.Serializable

@Serializable
data class NetworkShowcase(
  val header: NetworkHeader? = null,
  @Serializable(with = ContentsSerializer::class)
  val contents: NetworkContents = NetworkContents.Unknown,
  val footer: NetworkFooter? = null
)