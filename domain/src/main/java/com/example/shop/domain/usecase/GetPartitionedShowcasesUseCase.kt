package com.example.shop.domain.usecase

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Partitionable
import com.example.shop.domain.model.Showcase
import com.example.shop.domain.model.copy

class GetPartitionedShowcasesUseCase(
  private val showcaseRepository: ShowcaseRepository,
) {
  suspend operator fun invoke(
    partitionCounts: Map<String, Int>,
  ): List<Showcase> {
    return showcaseRepository.loadShowcases().map { showcase ->
      if (showcase.contents is Partitionable) {
        showcase.partition(partitionCounts)
      } else {
        showcase
      }
    }
  }

  private fun Showcase.partition(
    partitionCounts: Map<String, Int>,
  ): Showcase {
    val partitionCount = partitionCounts[id] ?: (contents as Partitionable).partitionInfo.defaultCount
    val hasMore = partitionCount < contents.items.size

    return this.copy(
      contents = contents.copy(
        items = contents.items.take(partitionCount)
      ),
      footer = when {
        hasMore -> null
        else -> footer
      }
    )
  }
}