package com.example.shop.domain.usecase

import com.example.shop.domain.FakeShowcaseRepository
import com.example.shop.domain.showcaseWith20ContentItem
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class RefreshShowcaseUseCaseTest : BehaviorSpec() {
  override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

  private val repository: FakeShowcaseRepository = FakeShowcaseRepository(
    listOf(showcaseWith20ContentItem)
  )
  private val useCase: RefreshShowcaseUseCase = RefreshShowcaseUseCase(
    showcaseRepository = repository
  )

  init {
    Given("showcaseId가 주어졌을 때") {
      val showcaseId = showcaseWith20ContentItem.id

      When("showcase가 repository에 존재하는 경우 usecase를 실행하면") {
        useCase(showcaseId)

        Then("repository의 showcase.contents는 실행 전과 동일한 아이템을 가지지만 순서만 다르다") {
          val updatedShowcase = repository.loadShowcases()[0]

          updatedShowcase shouldNotBe showcaseWith20ContentItem
          updatedShowcase.contents.items.toSet() shouldBe showcaseWith20ContentItem.contents.items.toSet()
        }
      }

      When("showcase가 repository에 존재하지 않는 경우 usecase를 실행하면") {
        useCase("Invalid Id")

        Then("repository의 showcase는 실행 전과 동일하다") {
          repository.loadShowcases() shouldBe listOf(showcaseWith20ContentItem)
        }
      }
    }
  }
}