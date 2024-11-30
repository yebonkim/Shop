package com.example.shop.feature.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Showcase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

data class HomeUiState(
  val showcases: Async<List<Showcase>> = Uninitialized,
) : MavericksState

class HomeViewModel @AssistedInject constructor(
  @Assisted initialState: HomeUiState,
  private val showcaseRepository: ShowcaseRepository,
) : MavericksViewModel<HomeUiState>(initialState) {

  init {
    loadData()
  }

  private fun loadData() {
    suspend {
      showcaseRepository.loadShowcases()
    }.execute { showcases ->
      copy(
        showcases = showcases,
      )
    }
  }

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<HomeViewModel, HomeUiState> {
    override fun create(state: HomeUiState): HomeViewModel
  }

  companion object :
    MavericksViewModelFactory<HomeViewModel, HomeUiState> by hiltMavericksViewModelFactory()
}