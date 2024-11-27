package com.example.shop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.shop.feature.home.Home
import com.example.shop.feature.home.HomeRoute

@Composable
fun ShopNavHost() {
  val navController = rememberNavController()

  NavHost(
    navController = navController, startDestination = Home
  ) {
    HomeRoute()
  }
}