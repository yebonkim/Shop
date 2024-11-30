package com.example.shop.designsystem.ui.showcase

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shop.designsystem.ui.component.UpdateWarning
import com.example.shop.designsystem.ui.showcase.layout.BannerPager
import com.example.shop.designsystem.ui.showcase.layout.HorizontalScroll
import com.example.shop.designsystem.ui.showcase.layout.goodsGrid
import com.example.shop.designsystem.ui.showcase.layout.styleGrid
import com.example.shop.domain.model.Contents
import com.example.shop.domain.model.Showcase

@Composable
fun ShowcaseList(
  modifier: Modifier = Modifier,
  showcases: List<Showcase>,
  onClickHeaderLink: (String) -> Unit,
  onClickFooter: (String) -> Unit,
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(3),
    modifier = modifier,
  ) {
    showcases.forEach { showcase ->
      showcase.header?.let { header ->
        item(span = { GridItemSpan(3) }) {
          ShowcaseHeader(
            header = header,
            onClickLink = { onClickHeaderLink(showcase.id) },
          )
        }
      }
      when (val content = showcase.contents) {
        is Contents.BannerContents -> {
          item(span = { GridItemSpan(3) }) {
            BannerPager(
              items = content.items,
            )
          }
        }

        is Contents.GridContents -> {
          goodsGrid(
            items = content.items,
          )
        }

        is Contents.ScrollContents -> {
          item(span = { GridItemSpan(3) }) {
            HorizontalScroll(
              items = content.items,
            )
          }
        }

        is Contents.StyleContents -> {
          styleGrid(
            items = content.items,
          )
        }

        is Contents.Unknown -> {
          item(span = { GridItemSpan(3) }) {
            UpdateWarning()
          }
        }
      }
      showcase.footer?.let { footer ->
        item(span = { GridItemSpan(3) }) {
          ShowcaseFooter(
            footer = footer,
            onClick = { onClickFooter(showcase.id) },
          )
        }
      }
    }
  }
}