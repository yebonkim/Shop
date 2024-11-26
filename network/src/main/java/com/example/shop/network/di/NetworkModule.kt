package com.example.shop.network.di

import com.example.shop.network.KtorClient
import com.example.shop.network.ShowcaseNet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Singleton
  @Provides
  fun provideHttpClient(): HttpClient {
    return KtorClient.createClient()
  }

  @Provides
  fun provideShowcaseNet(httpClient: HttpClient): ShowcaseNet {
    return ShowcaseNet(httpClient)
  }
}