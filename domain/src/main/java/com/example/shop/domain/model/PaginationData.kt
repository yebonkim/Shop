package com.example.shop.domain.model

data class PaginationData(
  val pageSize: Int,
  val page: Int = INITIAL_PAGE
) {
  companion object {
    private const val INITIAL_PAGE = 1
  }
}
