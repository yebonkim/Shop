package com.example.shop.domain.usecase

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.copy

class RefreshShowcaseUseCase(
  private val showcaseRepository: ShowcaseRepository
) {
  suspend operator fun invoke(showcaseId: String) {
    showcaseRepository.update(
      showcaseRepository.loadShowcases().map { showcase ->
        if (showcase.id == showcaseId) {
          showcase.copy(contents = showcase.contents.copy(showcase.contents.items.shuffled()))
        } else {
          showcase
        }
      }
    )
  }
}