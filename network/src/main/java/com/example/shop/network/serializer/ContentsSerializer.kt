package com.example.shop.network.serializer

import com.example.shop.network.model.NetworkContents
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object ContentsSerializer : JsonContentPolymorphicSerializer<NetworkContents>(NetworkContents::class) {
  override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out NetworkContents> {
    return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
      "BANNER" -> NetworkContents.BannerContents.serializer()
      "GRID" -> NetworkContents.GridContents.serializer()
      "SCROLL" -> NetworkContents.ScrollContents.serializer()
      "STYLE" -> NetworkContents.StyleContents.serializer()
      else -> NetworkContents.Unknown.serializer()
    }
  }
}