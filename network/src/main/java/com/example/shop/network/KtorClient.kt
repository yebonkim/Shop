package com.example.shop.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object KtorClient {
  private const val MUSINSA_HOST = "meta.musinsa.com"

  fun createClient(): HttpClient =
    HttpClient(Android) {
      defaultRequest {
        url {
          this.protocol = URLProtocol.HTTPS
          this.host = MUSINSA_HOST
          this.port = DEFAULT_PORT
        }
      }
      install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
      }
      install(ContentNegotiation) {
        json(
          Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
          }
        )
      }
      install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.ALL
      }
    }
}