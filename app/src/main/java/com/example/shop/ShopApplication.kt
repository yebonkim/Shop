package com.example.shop

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShopApplication: Application() {
  override fun onCreate() {
    super.onCreate()
    Mavericks.initialize(this)
  }
}