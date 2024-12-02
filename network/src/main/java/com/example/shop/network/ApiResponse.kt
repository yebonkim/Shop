package com.example.shop.network

sealed interface ApiResponse<out Response> {
  data class Success<RESPONSE>(val data: RESPONSE) : ApiResponse<RESPONSE>
  data class Error<Nothing>(val throwable: Throwable) : ApiResponse<Nothing>
}