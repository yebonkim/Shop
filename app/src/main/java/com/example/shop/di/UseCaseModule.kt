package com.example.shop.di

import com.example.shop.domain.usecase.SplitShowcaseByPageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {
  @Provides
  fun provideSplitShowcaseByPageUseCase(): SplitShowcaseByPageUseCase {
    return SplitShowcaseByPageUseCase()
  }
}