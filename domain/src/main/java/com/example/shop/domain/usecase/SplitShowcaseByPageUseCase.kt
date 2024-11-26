package com.example.shop.domain.usecase

import com.example.shop.domain.model.Contents
import com.example.shop.domain.model.PaginationData
import com.example.shop.domain.model.Showcase

class SplitShowcaseByPageUseCase {
  operator fun invoke(list: List<Showcase>, paginationData: Map<String, PaginationData>): List<Showcase> {
    return list.map {
      val pageData = paginationData[it.id]
      val contentsSize = it.contents.size()

      it.copy(
        contents = it.contents.subList(
          pageData?.pageStartPosition() ?: 0,
          pageData?.pageEndPosition()?.coerceAtMost(contentsSize) ?: contentsSize
        )
      )
    }
  }

  private fun Contents.subList(fromIndex: Int, toIndex: Int) = when (this) {
    is Contents.BannerContents -> Contents.BannerContents(list.subList(fromIndex, toIndex))
    is Contents.GridContents -> Contents.GridContents(list.subList(fromIndex, toIndex))
    is Contents.ScrollContents -> Contents.ScrollContents(list.subList(fromIndex, toIndex))
    is Contents.StyleContents -> Contents.StyleContents(list.subList(fromIndex, toIndex))
  }

  private fun Contents.size() = when (this) {
    is Contents.BannerContents -> list.size
    is Contents.GridContents -> list.size
    is Contents.ScrollContents -> list.size
    is Contents.StyleContents -> list.size
  }

  private fun PaginationData.pageStartPosition() = (page - 1) * pageSize
  private fun PaginationData.pageEndPosition() = page * pageSize
}