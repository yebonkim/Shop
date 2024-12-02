package com.example.shop.domain.usecase

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.copy

class RefreshShowcaseUseCase(
  private val showcaseRepository: ShowcaseRepository
) {
  suspend operator fun invoke(showcaseId: String) {
    val showcases = showcaseRepository.loadShowcases().getOrDefault(emptyList())

    showcaseRepository.update(showcases.map { showcase ->
      if (showcase.id == showcaseId) {
        showcase.copy(contents = showcase.contents.copy(showcase.contents.items.shuffled()))
      } else {
        showcase
      }
    })
  }
}
