package com.example.shop.domain.usecase

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Partitionable
import com.example.shop.domain.model.Showcase
import com.example.shop.domain.model.copy

class LoadPartitionedShowcasesUseCase(
  private val showcaseRepository: ShowcaseRepository,
) {
  suspend operator fun invoke(
    idToPartitionCount: Map<String, Int>,
  ): Result<List<Showcase>> {
    return showcaseRepository.loadShowcases().map { showcases ->
      showcases.map { showcase ->
        if (showcase.contents is Partitionable) {
          showcase.partition(idToPartitionCount)
        } else {
          showcase
        }
      }
    }
  }

  private fun Showcase.partition(
    idToPartitionCount: Map<String, Int>,
  ): Showcase {
    val partitionCount =
      idToPartitionCount[id] ?: (contents as Partitionable).partitionInfo.defaultCount
    val hasMore = partitionCount < contents.items.size

    return this.copy(
      contents = contents.copy(
        items = contents.items.take(partitionCount)
      ),
      footer = when {
        hasMore -> footer
        else -> null
      }
    )
  }
}