package com.example.shop.domain

import com.example.shop.domain.model.Showcase
import kotlinx.coroutines.flow.Flow

interface ShowcaseRepository {
  suspend fun loadShowcases(): List<Showcase>
  suspend fun update(showcases: List<Showcase>)
}