package com.example.shop.designsystem.ui.showcase.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.shop.designsystem.ui.showcase.item.BannerItem
import com.example.shop.domain.model.ContentsItemType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BannerPager(
  modifier: Modifier = Modifier,
  banners: ImmutableList<ContentsItemType.Banner>,
  autoScroll: Boolean = true,
  onClickBanner: (ContentsItemType.Banner) -> Unit = {},
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
      contentPadding = PaddingValues(horizontal = 12.dp),
    ) { pageIdx ->
      BannerItem(
        imageModifier = Modifier.parallax(pagerState, pageIdx),
        banner = banners[pageIdx % banners.size],
        onClick = { onClickBanner(banners[pageIdx % banners.size]) },
      )
    }
    PagerIndicator(
      currentPage = (pagerState.currentPage % banners.size) + 1,
      pageCount = banners.size,
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(horizontal = 20.dp, vertical = 8.dp)
    )
  }
}

private fun Modifier.parallax(state: PagerState, currentPage: Int) =
  layout { measurable, constraints ->
    val expandedConstraints = Constraints(
      minWidth = (constraints.minWidth * 1.05).toInt(),
      maxWidth = (constraints.maxWidth * 1.05).toInt(),
      minHeight = (constraints.minHeight * 1.05).toInt(),
      maxHeight = (constraints.maxHeight * 1.05).toInt()
    )
    val placeable = measurable.measure(expandedConstraints)

    val direction = state.currentPage - currentPage
    val fraction = (direction + state.currentPageOffsetFraction).coerceIn(-1f, 1f)
    val xPos = (fraction / 3 * placeable.width).toInt()

    layout(placeable.width, placeable.height) {
      placeable.place(x = xPos, y = 0, zIndex = 0f)
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