package com.example.shop.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.example.shop.feature.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {
  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  fun mainViewModelFactory(factory: HomeViewModel.Factory): AssistedViewModelFactory<*, *>
}