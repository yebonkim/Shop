package com.example.shop.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object Home

fun NavGraphBuilder.HomeRoute(navigateToDetail: (String) -> Unit) {
  composable<Home> {
    HomeScreen(navigateToDetail = navigateToDetail)
  }
}