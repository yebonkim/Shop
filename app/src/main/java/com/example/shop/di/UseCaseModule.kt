package com.example.shop.di

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.usecase.LoadPartitionedShowcasesUseCase
import com.example.shop.domain.usecase.RefreshShowcaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
  @Provides
  fun provideLoadPartitionedShowcasesUseCase(
    showcaseRepository: ShowcaseRepository
  ): LoadPartitionedShowcasesUseCase {
    return LoadPartitionedShowcasesUseCase(showcaseRepository)
  }

  @Provides
  fun provideRefreshShowcaseUseCase(
    showcaseRepository: ShowcaseRepository
  ): RefreshShowcaseUseCase {
    return RefreshShowcaseUseCase(showcaseRepository)
  }
}