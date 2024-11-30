package com.example.shop.domain.model

interface Partitionable {
  val partitionInfo: PartitionInfo
}

data class PartitionInfo(
  val defaultCount: Int,
  val fetchCount: Int,
)