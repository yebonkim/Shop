package com.example.shop.feature.home

import androidx.compose.runtime.Stable
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.shop.domain.model.FooterType
import com.example.shop.domain.model.Partitionable
import com.example.shop.domain.model.Showcase
import com.example.shop.domain.usecase.LoadPartitionedShowcasesUseCase
import com.example.shop.domain.usecase.RefreshShowcaseUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.net.URL
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@Stable
data class HomeUiState(
  val showcases: Async<ImmutableList<Showcase>> = Uninitialized,
) : MavericksState

sealed interface HomeUiEffect {
  data class NavigateToDetail(val url: String) : HomeUiEffect
}

class HomeViewModel @AssistedInject constructor(
  @Assisted initialState: HomeUiState,
  private val loadPartitionedShowcasesUseCase: LoadPartitionedShowcasesUseCase,
  private val refreshShowcaseUseCase: RefreshShowcaseUseCase,
) : MavericksViewModel<HomeUiState>(initialState) {

  val uiEffect: Channel<HomeUiEffect> = Channel()

  init {
    loadData()
  }

  fun loadData() {
    withState { state ->
      loadData(state.showcases()?.itemSizeMap() ?: emptyMap())
    }
  }

  private fun loadData(partitionInfos: Map<String, Int>) {
    viewModelScope.launch {
      setState { copy(showcases = Loading(value = showcases())) }
      loadPartitionedShowcasesUseCase(partitionInfos).fold(
        onSuccess = { setState { copy(showcases = Success(it.toImmutableList())) } },
        onFailure = { setState { copy(showcases = Fail(it)) } }
      )
    }
  }

  private fun List<Showcase>.itemSizeMap(): Map<String, Int> {
    return associate { showcase ->
      showcase.id to showcase.contents.items.size
    }
  }

  fun onClickFooter(showcaseId: String) {
    withState { state ->
      state.showcases()?.let { showcases ->
        val footerType = showcases.find { it.id == showcaseId }?.footer?.type ?: return@let

        when (footerType) {
          FooterType.MORE -> loadMore(showcaseId)
          FooterType.REFRESH -> refresh(showcaseId)
        }
      }
    }
  }

  fun onClickLink(url: String?) {
    if (url == null || !url.isValidUrl()) return

    viewModelScope.launch {
      uiEffect.send(HomeUiEffect.NavigateToDetail(url))
    }
  }

  private fun String.isValidUrl(): Boolean = runCatching {
    URL(this)
  }.isSuccess

  private fun loadMore(showcaseId: String) {
    withState { state ->
      val showcases = state.showcases() ?: return@withState
      val contents = showcases.find { it.id == showcaseId }?.contents ?: return@withState

      if (contents is Partitionable) {
        val partitionInfos = showcases.itemSizeMap().toMutableMap().apply {
          this[showcaseId] = contents.items.size + contents.partitionInfo.fetchCount
        }

        loadData(partitionInfos)
      }
    }
  }

  private fun refresh(showcaseId: String) {
    viewModelScope.launch {
      refreshShowcaseUseCase(showcaseId)
      loadData()
    }
  }

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<HomeViewModel, HomeUiState> {
    override fun create(state: HomeUiState): HomeViewModel
  }

  companion object :
    MavericksViewModelFactory<HomeViewModel, HomeUiState> by hiltMavericksViewModelFactory()
}