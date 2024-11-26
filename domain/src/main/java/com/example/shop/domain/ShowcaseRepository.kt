package com.example.shop.domain

import com.example.shop.domain.model.Showcase
import kotlinx.coroutines.flow.Flow

interface ShowcaseRepository {
  fun load(): Flow<List<Showcase>>
}