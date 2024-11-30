package com.example.shop.domain

import com.example.shop.domain.model.Contents
import com.example.shop.domain.model.ContentsItemType
import com.example.shop.domain.model.Footer
import com.example.shop.domain.model.FooterType
import com.example.shop.domain.model.PartitionInfo
import com.example.shop.domain.model.Showcase

val partitionableShowcase = Showcase(
  id = "1",
  header = null,
  contents = Contents.StyleContents(
    partitionInfo = PartitionInfo(
      defaultCount = 2,
      fetchCount = 2,
    ),
    items = listOf(
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style1.jpg",
        linkUrl = "https://example.com/style1",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style2.jpg",
        linkUrl = "https://example.com/style2",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style3.jpg",
        linkUrl = "https://example.com/style3",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style4.jpg",
        linkUrl = "https://example.com/style4",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style5.jpg",
        linkUrl = "https://example.com/style5",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style6.jpg",
        linkUrl = "https://example.com/style6",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style7.jpg",
        linkUrl = "https://example.com/style7",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style8.jpg",
        linkUrl = "https://example.com/style8",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style9.jpg",
        linkUrl = "https://example.com/style9",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style10.jpg",
        linkUrl = "https://example.com/style10",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style11.jpg",
        linkUrl = "https://example.com/style11",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style12.jpg",
        linkUrl = "https://example.com/style12",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style13.jpg",
        linkUrl = "https://example.com/style13",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style14.jpg",
        linkUrl = "https://example.com/style14",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style15.jpg",
        linkUrl = "https://example.com/style15",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style16.jpg",
        linkUrl = "https://example.com/style16",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style17.jpg",
        linkUrl = "https://example.com/style17",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style18.jpg",
        linkUrl = "https://example.com/style18",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style19.jpg",
        linkUrl = "https://example.com/style19",
      ),
      ContentsItemType.Style(
        thumbnailUrl = "https://example.com/style20.jpg",
        linkUrl = "https://example.com/style20",
      ),
    )
  ),
  footer = Footer(
    type = FooterType.MORE,
    title = "More",
  ),
)

val emptyPartitionableShowcase = Showcase(
  id = "2",
  header = null,
  contents = Contents.StyleContents(
    partitionInfo = PartitionInfo(
      defaultCount = 2,
      fetchCount = 2,
    ),
    items = emptyList(),
  ),
  footer = Footer(
    type = FooterType.MORE,
    title = "More",
  ),
)

val unpartitionableShowcase = Showcase(
  id = "3",
  header = null,
  contents = Contents.BannerContents(
    items = listOf(
      ContentsItemType.Banner(
        title = "Banner1",
        description = "Banner1 description",
        keyword = "Banner1 keyword",
        linkUrl = "https://example.com/banner1",
        thumbnailUrl = "https://example.com/banner1.jpg",
      ),
      ContentsItemType.Banner(
        title = "Banner2",
        description = "Banner2 description",
        keyword = "Banner2 keyword",
        linkUrl = "https://example.com/banner2",
        thumbnailUrl = "https://example.com/banner2.jpg",
      ),
      ContentsItemType.Banner(
        title = "Banner3",
        description = "Banner3 description",
        keyword = "Banner3 keyword",
        linkUrl = "https://example.com/banner3",
        thumbnailUrl = "https://example.com/banner3.jpg",
      ),
    )
  ),
  footer = Footer(
    type = FooterType.REFRESH,
    title = "Refresh",
  ),
)