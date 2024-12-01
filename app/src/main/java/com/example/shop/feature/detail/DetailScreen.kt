package com.example.shop.feature.detail

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Composable
fun DetailScreen(linkUrl: String) {
  Scaffold(
    modifier = Modifier
      .fillMaxSize()
  ) { padding ->
    AndroidView(
      factory = {
        WebView(it).apply {
          this.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
          )

          this.settings.javaScriptEnabled = true
          this.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
              view: WebView?,
              request: WebResourceRequest?
            ): Boolean {
              return false
            }
          }
          this.webChromeClient = WebChromeClient()
        }
      },
      modifier = Modifier.padding(padding),
      update = {
        it.loadUrl(linkUrl)
      }
    )
  }
}