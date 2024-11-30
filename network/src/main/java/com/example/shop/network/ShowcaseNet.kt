package com.example.shop.network

import com.example.shop.network.model.NetworkShowcaseRespond
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import javax.inject.Inject

class ShowcaseNet @Inject constructor(
  private val httpClient: HttpClient
) {
  suspend fun loadShowcase(): ApiResponse<NetworkShowcaseRespond> = runCatching {
    httpClient.request {
      method = HttpMethod.Get
      url {
        appendPathSegments("interview", "list.json")
      }
    }.body<NetworkShowcaseRespond>()
  }.fold(
    onSuccess = { ApiResponse.Success(it) },
    onFailure = { ApiResponse.Error(it.message ?: "Unknown error") }
  )
}