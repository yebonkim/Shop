package com.example.shop.data.di

import com.example.shop.data.ShowcaseRepositoryImpl
import com.example.shop.domain.ShowcaseRepository
import com.example.shop.network.ShowcaseNet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Singleton
  @Provides
  fun provideShowcaseRepository(
    showcaseNet: ShowcaseNet,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
  ): ShowcaseRepository {
    return ShowcaseRepositoryImpl(
      network = showcaseNet,
      ioDispatcher = ioDispatcher
    )
  }
}