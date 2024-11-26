package com.example.shop.data

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Showcase
import com.example.shop.network.ApiResponse
import com.example.shop.network.ShowcaseNet
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShowcaseRepositoryImpl(
  private val network: ShowcaseNet
) : ShowcaseRepository {

  override fun load(): Flow<List<Showcase>> =
    network.loadShowcase()
      .map { response ->
        when (response) {
          is ApiResponse.Success -> {
            response.data.data.map { it.toDomain(generateRandomId()) }
          }

          else -> emptyList()
        }
      }

  private fun generateRandomId(): String = UUID.randomUUID().toString()
}