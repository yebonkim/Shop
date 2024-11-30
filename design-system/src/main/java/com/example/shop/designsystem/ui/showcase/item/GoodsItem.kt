package com.example.shop.designsystem.ui.showcase.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.shop.designsystem.R
import com.example.shop.designsystem.theme.ShopTheme
import com.example.shop.designsystem.ui.component.AsyncImage
import com.example.shop.domain.model.ContentsItemType

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GoodsItem(
  modifier: Modifier = Modifier,
  goods: ContentsItemType.Goods
) {
  Column(
    modifier = modifier
      .width(120.dp)
      .padding(bottom = 4.dp),
  ) {
    Box {
      AsyncImage(
        model = goods.thumbnailUrl,
        contentDescription = goods.brandName,
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(0.8f)
          .padding(bottom = 4.dp),
        contentScale = ContentScale.Crop
      )
      if (goods.hasCoupon) {
        GoodsCoupon(
          modifier = Modifier.align(Alignment.BottomStart)
        )
      }
    }
    Text(
      text = goods.brandName,
      modifier = Modifier.padding(4.dp),
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      overflow = TextOverflow.Ellipsis,
      maxLines = 1,
      style = MaterialTheme.typography.bodySmall,
    )
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.Bottom,
    ) {
      Text(
        text = stringResource(R.string.priceFormat, goods.price),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyLarge,
      )
      Text(
        text = stringResource(R.string.percentage, goods.saleRate),
        color = MaterialTheme.colorScheme.scrim,
        style = MaterialTheme.typography.labelLarge,
      )
    }
  }
}

@Composable
private fun GoodsCoupon(
  modifier: Modifier = Modifier
) {
  Text(
    text = stringResource(R.string.coupon),
    modifier = modifier
      .background(MaterialTheme.colorScheme.secondary)
      .padding(horizontal = 6.dp, vertical = 4.dp),
    color = MaterialTheme.colorScheme.onSecondary,
    style = MaterialTheme.typography.labelMedium,
  )
}

@Composable
@Preview
fun GoodsPreview() {
  ShopTheme {
    GoodsItem(
      goods = ContentsItemType.Goods(
        brandName = "Goods",
        price = 1000,
        hasCoupon = true,
        linkUrl = "",
        saleRate = 50,
        thumbnailUrl = "https://image.msscdn.net/images/goods_img/20201221/1727824/1727824_4_320.jpg",
      )
    )
  }
}

@Composable
@Preview
fun CouponPreview() {
  ShopTheme {
    GoodsCoupon()
  }
}