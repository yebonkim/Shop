package com.example.shop.data.di

import com.example.shop.data.ShowcaseRepositoryImpl
import com.example.shop.domain.ShowcaseRepository
import com.example.shop.network.ShowcaseNet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

  @Provides
  fun provideShowcaseRepository(
    showcaseNet: ShowcaseNet
  ): ShowcaseRepository {
    return ShowcaseRepositoryImpl(showcaseNet)
  }
}