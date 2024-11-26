package com.example.shop.network

import com.example.shop.network.model.NetworkShowcaseRespond
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowcaseNet @Inject constructor(
  private val httpClient: HttpClient
) {
  fun loadShowcase(): Flow<ApiResponse<NetworkShowcaseRespond>> = flow {
    runCatching {
      httpClient.request {
        method = HttpMethod.Get
        url {
          appendPathSegments("interview", "list.json")
        }
      }.body<NetworkShowcaseRespond>()
    }.onSuccess { data ->
      emit(ApiResponse.Success(data))
    }.onFailure { exception ->
      emit(ApiResponse.Error(exception.message ?: "Unknown error"))
    }
  }
}