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