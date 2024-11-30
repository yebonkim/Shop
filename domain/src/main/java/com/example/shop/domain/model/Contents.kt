package com.example.shop.domain.model

sealed interface Contents {
  val items: List<ContentsItemType>

  data class BannerContents(
    override val items: List<ContentsItemType.Banner>
  ) : Contents

  data class GridContents(
    override val partitionInfo: PartitionInfo,
    override val items: List<ContentsItemType.Goods>,
  ) : Contents, Partitionable

  data class ScrollContents(
    override val items: List<ContentsItemType.Goods>
  ) : Contents

  data class StyleContents(
    override val partitionInfo: PartitionInfo,
    override val items: List<ContentsItemType.Style>
  ) : Contents, Partitionable

  data class Unknown(
    override val items: List<ContentsItemType>
  ) : Contents
}

fun Contents.copy(items: List<ContentsItemType>): Contents {
  return when (this) {
    is Contents.BannerContents -> {
      validateItems<ContentsItemType.Banner>(items)
      copy(items = items as List<ContentsItemType.Banner>)
    }
    is Contents.GridContents -> {
      validateItems<ContentsItemType.Goods>(items)
      copy(items = items as List<ContentsItemType.Goods>)
    }
    is Contents.ScrollContents -> {
      validateItems<ContentsItemType.Goods>(items)
      copy(items = items as List<ContentsItemType.Goods>)
    }
    is Contents.StyleContents -> {
      validateItems<ContentsItemType.Style>(items)
      copy(items = items as List<ContentsItemType.Style>)
    }
    is Contents.Unknown -> {
      copy(items = items)
    }
  }
}

private inline fun <reified T : ContentsItemType> validateItems(items: List<ContentsItemType>) {
  if (items.any { it !is T }) {
    throw IllegalArgumentException("Expected ContentsType.${T::class.java.simpleName}, but found: ${items.filter { it !is T }}")
  }
}