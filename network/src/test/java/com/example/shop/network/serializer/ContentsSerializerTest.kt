package com.example.shop.network.serializer

import com.example.shop.network.model.NetworkShowcaseRespond
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json

class ContentsSerializerTest: BehaviorSpec() {
  override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

  private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
  }

  init {
    Given("ContentsSerializer가 주어졌을 때") {
      When("빈 데이터가 주어졌을 때") {
        val contents = SampleData.emptyJsonContents

        Then("빈 데이터를 반환한다") {
          json.decodeFromString<NetworkShowcaseRespond>(contents) shouldBe SampleData.emptyRespond
        }
      }

      When("정의된 Contents 데이터들이 주어졌을 때") {
        val contents = SampleData.defaultJsonContents

        Then("형식에 맞는 데이터를 얻는다") {
          json.decodeFromString<NetworkShowcaseRespond>(contents) shouldBe SampleData.defaultRespond
        }
      }

      When("정의되지 않은 Contents 데이터가 주어졌을 때") {
        val contents = SampleData.invalidJsonContents

        Then("Unknown으로 처리된 데이터를 얻는다") {
          json.decodeFromString<NetworkShowcaseRespond>(contents) shouldBe SampleData.invalidRespond
        }
      }
    }
  }
}