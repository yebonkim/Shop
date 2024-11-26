package com.example.shop.data

import com.example.shop.domain.model.Banner
import com.example.shop.domain.model.Contents
import com.example.shop.domain.model.Footer
import com.example.shop.domain.model.FooterType
import com.example.shop.domain.model.Goods
import com.example.shop.domain.model.Header
import com.example.shop.domain.model.Showcase
import com.example.shop.domain.model.Style
import com.example.shop.network.model.NetworkBanner
import com.example.shop.network.model.NetworkContents
import com.example.shop.network.model.NetworkFooter
import com.example.shop.network.model.NetworkGoods
import com.example.shop.network.model.NetworkHeader
import com.example.shop.network.model.NetworkShowcase
import com.example.shop.network.model.NetworkStyle

internal fun NetworkBanner.toDomain() = Banner(
  title = title,
  description = description,
  keyword = keyword,
  linkUrl = linkUrl,
  thumbnailUrl = thumbnailUrl
)

internal fun NetworkGoods.toDomain() = Goods(
  brandName = brandName,
  price = price,
  saleRate = saleRate,
  hasCoupon = hasCoupon,
  linkUrl = linkUrl,
  thumbnailUrl = thumbnailUrl
)

internal fun NetworkStyle.toDomain() = Style(
  linkUrl = linkUrl,
  thumbnailUrl = thumbnailUrl
)

internal fun NetworkHeader.toDomain() = Header(
  title = title,
  iconUrl = iconUrl,
  linkUrl = linkUrl
)

internal fun NetworkFooter.toDomain() = when (this) {
  is NetworkFooter.MoreFooter -> Footer(
    type = FooterType.MORE,
    title = title,
    iconUrl = iconUrl
  )
  is NetworkFooter.RefreshFooter -> Footer(
    type = FooterType.REFRESH,
    title = title,
    iconUrl = iconUrl
  )
}

internal fun NetworkContents.toDomain() = when (this) {
  is NetworkContents.BannerContents -> Contents.BannerContents(banners.map { it.toDomain() })
  is NetworkContents.GridContents -> Contents.GridContents(goods.map { it.toDomain() })
  is NetworkContents.ScrollContents -> Contents.ScrollContents(goods.map { it.toDomain() })
  is NetworkContents.StyleContents -> Contents.StyleContents(styles.map { it.toDomain() })
}

internal fun NetworkShowcase.toDomain(id: String) = Showcase(
  id = id,
  header = header?.toDomain(),
  contents = contents.toDomain(),
  footer = footer?.toDomain()
)