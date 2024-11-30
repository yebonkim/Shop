package com.example.shop.data

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Showcase
import com.example.shop.network.ApiResponse
import com.example.shop.network.ShowcaseNet
import java.util.UUID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ShowcaseRepositoryImpl(
  private val network: ShowcaseNet,
  private val ioDispatcher: CoroutineDispatcher
) : ShowcaseRepository {
  private var cachedShowcases: List<Showcase> = listOf()

  override suspend fun loadShowcases(): List<Showcase> {
    if (cachedShowcases.isNotEmpty()) {
      return cachedShowcases
    }
    return withContext(ioDispatcher) {
      when (val loaded = network.loadShowcase()) {
        is ApiResponse.Success -> {
          cachedShowcases = loaded.data.data.map { it.toDomain(generateRandomId()) }
          cachedShowcases
        }

        else -> emptyList()
      }
    }
  }

  override suspend fun update(showcases: List<Showcase>) {
    cachedShowcases = showcases
  }

  private fun generateRandomId(): String = UUID.randomUUID().toString()
}