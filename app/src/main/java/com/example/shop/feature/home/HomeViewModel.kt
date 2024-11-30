package com.example.shop.feature.home

import androidx.compose.runtime.Stable
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.shop.domain.model.Showcase
import com.example.shop.domain.usecase.GetPartitionedShowcasesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@Stable
data class HomeUiState(
  val showcases: Async<List<Showcase>> = Uninitialized,
) : MavericksState

class HomeViewModel @AssistedInject constructor(
  @Assisted initialState: HomeUiState,
  private val getPartitionedShowcasesUseCase: GetPartitionedShowcasesUseCase,
) : MavericksViewModel<HomeUiState>(initialState) {

  init {
    loadData()
  }

  private fun loadData() {
    withState { state ->
      loadData(state.showcases()?.itemSizeMap() ?: emptyMap())
    }
  }

  private fun loadData(partitionInfos: Map<String, Int>) {
    suspend {
      getPartitionedShowcasesUseCase(partitionInfos)
    }.execute(retainValue = HomeUiState::showcases) { showcases ->
      copy(showcases = showcases)
    }
  }

  private fun List<Showcase>.itemSizeMap(): Map<String, Int> {
    return associate { showcase ->
      showcase.id to showcase.contents.items.size
    }
  }

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<HomeViewModel, HomeUiState> {
    override fun create(state: HomeUiState): HomeViewModel
  }

  companion object :
    MavericksViewModelFactory<HomeViewModel, HomeUiState> by hiltMavericksViewModelFactory()
}