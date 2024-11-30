package com.example.shop.domain.model

data class Showcase(
  val id: String,
  val header: Header?,
  val contents: Contents,
  val footer: Footer?,
)