package com.example.shop.di

import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.usecase.GetPartitionedShowcasesUseCase
import com.example.shop.domain.usecase.RefreshShowcaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
  @Provides
  fun provideGetPartitionedShowcasesUseCase(
    showcaseRepository: ShowcaseRepository
  ): GetPartitionedShowcasesUseCase {
    return GetPartitionedShowcasesUseCase(showcaseRepository)
  }

  @Provides
  fun provideRefreshShowcaseUseCase(
    showcaseRepository: ShowcaseRepository
  ): RefreshShowcaseUseCase {
    return RefreshShowcaseUseCase(showcaseRepository)
  }
}