package com.example.shop.domain.model

data class Footer(
  val type: FooterType,
  val title: String,
  val iconUrl: String? = null
)
