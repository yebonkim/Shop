package com.example.shop.domain.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

sealed interface Contents {
  val items: ImmutableList<ContentsItemType>

  data class BannerContents(
    override val items: ImmutableList<ContentsItemType.Banner>
  ) : Contents

  data class GridContents(
    override val partitionInfo: PartitionInfo,
    override val items: ImmutableList<ContentsItemType.Goods>,
  ) : Contents, Partitionable

  data class ScrollContents(
    override val items: ImmutableList<ContentsItemType.Goods>
  ) : Contents

  data class StyleContents(
    override val partitionInfo: PartitionInfo,
    override val items: ImmutableList<ContentsItemType.Style>
  ) : Contents, Partitionable

  data class Unknown(
    override val items: ImmutableList<ContentsItemType>
  ) : Contents
}

fun Contents.copy(items: List<ContentsItemType>): Contents {
  return when (this) {
    is Contents.BannerContents -> {
      validateItems<ContentsItemType.Banner>(items)
      copy(items = items.toImmutableList() as ImmutableList<ContentsItemType.Banner>)
    }
    is Contents.GridContents -> {
      validateItems<ContentsItemType.Goods>(items)
      copy(items = items.toImmutableList() as ImmutableList<ContentsItemType.Goods>)
    }
    is Contents.ScrollContents -> {
      validateItems<ContentsItemType.Goods>(items)
      copy(items = items.toImmutableList() as ImmutableList<ContentsItemType.Goods>)
    }
    is Contents.StyleContents -> {
      validateItems<ContentsItemType.Style>(items)
      copy(items = items.toImmutableList() as ImmutableList<ContentsItemType.Style>)
    }
    is Contents.Unknown -> {
      copy(items = items.toImmutableList())
    }
  }
}

private inline fun <reified T : ContentsItemType> validateItems(items: List<ContentsItemType>) {
  if (items.any { it !is T }) {
    throw IllegalArgumentException("Expected ContentsType.${T::class.java.simpleName}, but found: ${items.filter { it !is T }}")
  }
}