package com.example.shop.feature.detail

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material3.Text
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class Detail(val linkUrl: String)

fun NavController.navigateToDetail(linkUrl: String) {
  navigate(route = Detail(linkUrl  = linkUrl))
}

fun NavGraphBuilder.DetailRoute() {
  composable<Detail> { backStackEntry ->
  }
}