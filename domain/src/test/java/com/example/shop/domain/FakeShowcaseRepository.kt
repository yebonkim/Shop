package com.example.shop.domain

import com.example.shop.domain.model.Showcase

class FakeShowcaseRepository(
  var data: List<Showcase> = emptyList()
): ShowcaseRepository {
  override suspend fun loadShowcases(): List<Showcase> = data

  override suspend fun update(showcases: List<Showcase>) {
    data = showcases
  }
}