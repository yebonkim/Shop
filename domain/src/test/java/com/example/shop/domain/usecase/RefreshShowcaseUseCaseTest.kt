package com.example.shop.domain.usecase

import com.example.shop.domain.Fixtures
import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Showcase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.mockk

class RefreshShowcaseUseCaseTest : BehaviorSpec() {
  override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

  private var repositoryData = Result.success(listOf(Fixtures.refreshableShowcase))
  private val repository: ShowcaseRepository = mockk()
  private val useCase: RefreshShowcaseUseCase = RefreshShowcaseUseCase(
    showcaseRepository = repository
  )

  override suspend fun beforeSpec(spec: Spec) {
    super.beforeSpec(spec)

    coEvery { repository.loadShowcases() } answers { repositoryData }
    coEvery { repository.update(any()) } answers {
      val showcases = firstArg<List<Showcase>>()
      repositoryData = Result.success(showcases)
    }
  }

  init {
    Given("showcaseId가 주어졌을 때") {
      val showcaseId = Fixtures.refreshableShowcase.id

      When("showcase가 repository에 존재하는 경우 usecase를 실행하면") {
        useCase(showcaseId)

        Then("repository의 showcase.contents는 실행 전과 동일한 아이템을 가지지만 순서만 다르다") {
          val updatedShowcase = repository.loadShowcases().getOrElse { emptyList() }.first()

          updatedShowcase shouldNotBe Fixtures.refreshableShowcase
          updatedShowcase.contents.items.toSet() shouldBe Fixtures.refreshableShowcase.contents.items.toSet()
        }
      }

      When("repository에 실패 데이터가 있는 경우") {
        repositoryData = Result.failure(Exception())

        And("존재하지 않는 showcaseId로 usecase를 실행할 때") {
          val invalidId = "Invalid Id"
          useCase(invalidId)

          Then("repository의 showcase는 실행 전과 동일하다") {
            repository.loadShowcases() shouldBe repositoryData
          }
        }
      }

      When("repository에 빈 데이터가 있는 경우") {
        repositoryData = Result.success(emptyList())

        And("존재하지 않는 showcaseId로 usecase를 실행할 때") {
          val invalidId = "Invalid Id"
          useCase(invalidId)

          Then("repository의 showcase는 실행 전과 동일하다") {
            repository.loadShowcases() shouldBe repositoryData
          }
        }
      }
    }
  }
}