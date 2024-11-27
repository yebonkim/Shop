package com.example.shop.feature.home

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.shop.domain.model.PaginationData
import com.example.shop.domain.model.Showcase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

data class HomeUiState(
  val isLoading: Boolean = false,
  val showcases: List<Showcase> = emptyList(),
  private val pageMap: Map<String, PaginationData> = emptyMap()
): MavericksState

class HomeViewModel @AssistedInject constructor(
  @Assisted initialState: HomeUiState,
) : MavericksViewModel<HomeUiState>(initialState) {

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<HomeViewModel, HomeUiState> {
    override fun create(state: HomeUiState): HomeViewModel
  }

  companion object : MavericksViewModelFactory<HomeViewModel, HomeUiState> by hiltMavericksViewModelFactory()
}