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
  onClickLink: (String?) -> Unit,
  onClickFooter: (String) -> Unit,
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(3),
    modifier = modifier,
  ) {
    showcases.forEach { showcase ->
      showcase.header?.let { header ->
        item(
          span = { GridItemSpan(3) },
          contentType = { "ShowcaseHeader" },
        ) {
          ShowcaseHeader(
            header = header,
            onClickLink = { onClickLink(header.linkUrl) },
          )
        }
      }
      when (val content = showcase.contents) {
        is Contents.BannerContents -> {
          item(
            span = { GridItemSpan(3) },
            contentType = { "BannerPager" },
          ) {
            BannerPager(
              banners = content.items,
              onClickBanner = { onClickLink(it.linkUrl) },
            )
          }
        }

        is Contents.GridContents -> {
          goodsGrid(
            goodsList = content.items,
            onClickGoods = { onClickLink(it.linkUrl) },
          )
        }

        is Contents.ScrollContents -> {
          item(
            span = { GridItemSpan(3) },
            contentType = { "HorizontalScroll" },
          ) {
            HorizontalScroll(
              goodsList = content.items,
              onClickItem = { onClickLink(it.linkUrl) },
            )
          }
        }

        is Contents.StyleContents -> {
          styleGrid(
            styles = content.items,
            onClickStyle = { onClickLink(it.linkUrl) },
          )
        }

        is Contents.Unknown -> {
          item(
            span = { GridItemSpan(3) },
            contentType = { "UpdateWarning" },
          ) {
            UpdateWarning()
          }
        }
      }
      showcase.footer?.let { footer ->
        item(
          span = { GridItemSpan(3) },
          contentType = { "ShowcaseFooter" },
        ) {
          ShowcaseFooter(
            footer = footer,
            onClick = { onClickFooter(showcase.id) },
          )
        }
      }
    }
  }
}