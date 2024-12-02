package com.example.shop.network.serializer

import com.example.shop.network.model.NetworkBanner
import com.example.shop.network.model.NetworkContents
import com.example.shop.network.model.NetworkFooter
import com.example.shop.network.model.NetworkGoods
import com.example.shop.network.model.NetworkHeader
import com.example.shop.network.model.NetworkShowcase
import com.example.shop.network.model.NetworkShowcaseRespond
import com.example.shop.network.model.NetworkStyle


object SampleData {
  val emptyJsonContents = """
      {
        "data": []
      }
  """.trimIndent()
  val emptyRespond = NetworkShowcaseRespond()

  val defaultJsonContents = """
          {
            "data": [
              {
                "contents": {
                  "type": "BANNER",
                  "banners": [
                    {
                      "title": "",
                      "description": "",
                      "keyword": "",
                      "linkURL": "https://www.musinsa.com/app/campaign/index/junebeautyfull",
                      "thumbnailURL": "https://image.msscdn.net/images/event_banner/2022061009432800000059650.jpg"
                    }
                  ]
                }
              },
              {
                "header": {
                  "title": "클리어런스"
                },
                "contents": {
                  "type": "GRID",
                  "goods": [
                    {
                      "linkURL": "https://www.musinsa.com/app/goods/2281818",
                      "thumbnailURL": "https://image.msscdn.net/images/goods_img/20211224/2281818/2281818_1_320.jpg",
                      "brandName": "아스트랄 프로젝션",
                      "price": 39900,
                      "saleRate": 50,
                      "hasCoupon": true
                    }
                  ]
                },
                "footer": {
                  "type": "MORE",
                  "title": "더보기",
                  "iconURL": ""
                }
              },
              {
                "contents": {
                "type": "SCROLL",
                  "goods": [
                    {
                      "linkURL": "https://www.musinsa.com/app/goods/1727824",
                      "thumbnailURL": "https://image.msscdn.net/images/goods_img/20201221/1727824/1727824_4_320.jpg",
                      "brandName": "디스커버리 익스페디션",
                      "price": 59500,
                      "saleRate": 50,
                      "hasCoupon": true
                    }
                  ]
                }
              },
              {
                "contents": {
                  "type": "STYLE",
                  "styles": [
                    {
                      "linkURL": "https://www.musinsa.com/app/styles/views/27417",
                      "thumbnailURL": "https://image.musinsa.com/images/style/list/2022062214302100000008217.jpg"
                    }
                  ]
                }
              }
            ]
          }
        """.trimIndent()

  val defaultRespond = NetworkShowcaseRespond(
    data = listOf(
      NetworkShowcase(
        header = null,
        contents = NetworkContents.BannerContents(
          banners = listOf(
            NetworkBanner(
              linkUrl = "https://www.musinsa.com/app/campaign/index/junebeautyfull",
              thumbnailUrl = "https://image.msscdn.net/images/event_banner/2022061009432800000059650.jpg",
              title = "",
              description = "",
              keyword = ""
            )
          )
        ),
        footer = null
      ),
      NetworkShowcase(
        header = NetworkHeader("클리어런스"),
        contents = NetworkContents.GridContents(
          goods = listOf(
            NetworkGoods(
              linkUrl = "https://www.musinsa.com/app/goods/2281818",
              thumbnailUrl = "https://image.msscdn.net/images/goods_img/20211224/2281818/2281818_1_320.jpg",
              brandName = "아스트랄 프로젝션",
              price = 39900,
              saleRate = 50,
              hasCoupon = true
            )
          )
        ),
        footer = NetworkFooter.MoreFooter("더보기", "")
      ),
      NetworkShowcase(
        header = null,
        contents = NetworkContents.ScrollContents(
          goods = listOf(
            NetworkGoods(
              linkUrl = "https://www.musinsa.com/app/goods/1727824",
              thumbnailUrl = "https://image.msscdn.net/images/goods_img/20201221/1727824/1727824_4_320.jpg",
              brandName = "디스커버리 익스페디션",
              price = 59500,
              saleRate = 50,
              hasCoupon = true
            )
          )
        ),
        footer = null
      ),
      NetworkShowcase(
        header = null,
        contents = NetworkContents.StyleContents(
          styles = listOf(
            NetworkStyle(
              linkUrl = "https://www.musinsa.com/app/styles/views/27417",
              thumbnailUrl = "https://image.musinsa.com/images/style/list/2022062214302100000008217.jpg"
            )
          )
        ),
        footer = null
      ),
    )
  )

  val invalidJsonContents = """
      {
        "data": [
          {
            "contents": {
              "type": "STYLE",
              "styles": [
                {
                  "linkURL": "https://www.musinsa.com/app/styles/views/27417",
                  "thumbnailURL": "https://image.musinsa.com/images/style/list/2022062214302100000008217.jpg"
                }
              ]
            }
          },
          {
            "contents": {
              "type": "SHORTS",
              "shorts": [
                {
                  "linkURL": "https://www.musinsa.com/app/styles/views/27417",
                  "thumbnailURL": "https://image.musinsa.com/images/style/list/2022062214302100000008217.jpg"
                }
              ]
            }
          }
        ]
      }
""".trimIndent()

  val invalidRespond = NetworkShowcaseRespond(
    data = listOf(
      NetworkShowcase(
        header = null,
        contents = NetworkContents.StyleContents(
          styles = listOf(
            NetworkStyle(
              linkUrl = "https://www.musinsa.com/app/styles/views/27417",
              thumbnailUrl = "https://image.musinsa.com/images/style/list/2022062214302100000008217.jpg"
            )
          )
        ),
        footer = null
      ),
      NetworkShowcase(
        header = null,
        contents = NetworkContents.Unknown,
        footer = null
      )
    )
  )
}





