package com.example.shop.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.item.BannerItem
import com.example.shop.domain.model.ContentsItemType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BannerPager(
  modifier: Modifier = Modifier,
  items: List<ContentsItemType.Banner>,
  autoScroll: Boolean = true,
) {
  val pagerState = rememberPagerState(pageCount = { Int.MAX_VALUE })
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(pagerState.currentPage) {
    if (autoScroll) {
      delay(3000L)
      coroutineScope.launch {
        pagerState.animateScrollToPage(pagerState.currentPage + 1)
      }
    }
  }

  Box(modifier = modifier) {
    HorizontalPager(
      state = pagerState,
      contentPadding = PaddingValues(horizontal = 12.dp)
    ) { pageIdx ->
      BannerItem(
        banner = items[pageIdx % items.size]
      )
    }
    PagerIndicator(
      currentPage = (pagerState.currentPage % items.size) + 1,
      pageCount = items.size,
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(horizontal = 20.dp, vertical = 8.dp)
    )
  }
}

@Composable
private fun PagerIndicator(
  currentPage: Int,
  pageCount: Int,
  modifier: Modifier = Modifier,
) {
  Text(
    text = "$currentPage / $pageCount",
    modifier = modifier
      .background(
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
      )
      .padding(horizontal = 12.dp, vertical = 4.dp),
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.labelMedium,
  )
}