package com.example.shop.designsystem.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        is Contents.BannerContents -> Unit

        is Contents.GridContents -> {
          grid(
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

        is Contents.StyleContents -> Unit

        is Contents.Unknown -> Unit
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