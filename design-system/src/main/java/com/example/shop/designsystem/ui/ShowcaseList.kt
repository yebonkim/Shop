package com.example.shop.designsystem.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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