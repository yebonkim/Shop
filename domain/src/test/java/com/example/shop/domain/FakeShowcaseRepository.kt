package com.example.shop.domain

import com.example.shop.domain.model.Showcase

class FakeShowcaseRepository(
  var initialData: List<Showcase> = emptyList()
): ShowcaseRepository {
  override suspend fun loadShowcases(): List<Showcase> = initialData

  override suspend fun update(showcases: List<Showcase>) {
    initialData = showcases
  }
}