package com.example.shop.domain.usecase

import com.example.shop.domain.Fixtures
import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Contents
import com.example.shop.domain.model.Showcase
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList

class LoadPartitionedShowcaseUseCaseTest : BehaviorSpec() {
  override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

  private var repositoryData = Result.success(emptyList<Showcase>())
  private val repository: ShowcaseRepository = mockk()
  private val useCase: LoadPartitionedShowcasesUseCase = LoadPartitionedShowcasesUseCase(
    showcaseRepository = repository
  )

  override suspend fun beforeSpec(spec: Spec) {
    super.beforeSpec(spec)

    coEvery { repository.loadShowcases() } answers { repositoryData }
  }

  init {
    Given("repository에 데이터가 빈 데이터인 경우") {
      repositoryData = Result.success(emptyList())

      When("빈 idToPartitionCount 가 주어졌을 때") {
        val idToPartitionCount: Map<String, Int> = emptyMap()

        Then("빈 값을 받는다") {
          useCase(idToPartitionCount) shouldBe repositoryData
        }
      }

      When("repository에 존재하는 id가 없는 idToPartitionCount 가 주어졌을 때") {
        val idToPartitionCount = mapOf("INVALID ID" to 3)

        Then("빈 값을 받는다") {
          useCase(idToPartitionCount) shouldBe repositoryData
        }
      }
    }

    Given("repository에 데이터가 실패 데이터인 경우") {
      repositoryData = Result.failure(Exception())

      When("빈 idToPartitionCount 가 주어졌을 때") {
        val idToPartitionCount: Map<String, Int> = emptyMap()

        Then("실패 데이터를 받는다") {
          useCase(idToPartitionCount) shouldBe repositoryData
        }
      }

      When("repository에 존재하는 id가 없는 idToPartitionCount 가 주어졌을 때") {
        val idToPartitionCount = mapOf("INVALID ID" to 3)

        Then("실패 데이터를 받는다") {
          useCase(idToPartitionCount) shouldBe repositoryData
        }
      }
    }

    Given("repository에 partitionable, partiable 하지 않은 데이터가 있는 경우") {
      val partitionableContents: Contents.StyleContents =
        Fixtures.partitionableShowcase.contents as Contents.StyleContents

      repositoryData = Result.success(listOf(Fixtures.unpartitionableShowcase, Fixtures.partitionableShowcase))

      When("빈 idToPartitionCount 가 주어졌을 때") {
        val idToPartitionCount: Map<String, Int> = emptyMap()

        Then("Partitionable하지 않은 데이터는 변경되지 않는다") {
          useCase(idToPartitionCount).get(0).contents shouldBe Fixtures.unpartitionableShowcase.contents
        }
        Then("Partitionable한 데이터는 기본 갯수로 partition된다") {
          useCase(idToPartitionCount).get(1).contents shouldBe partitionableContents.copy(
            items = partitionableContents.items.take(partitionableContents.partitionInfo.defaultCount).toImmutableList()
          )
        }
      }

      When("repository에 존재하는 id가 없는 idToPartitionCount 가 주어졌을 때") {
        val idToPartitionCount = mapOf("INVALID ID" to 3)

        Then("Partitionable하지 않은 데이터는 변경되지 않는다") {
          useCase(idToPartitionCount).get(0).contents shouldBe Fixtures.unpartitionableShowcase.contents
        }
        Then("Partitionable한 데이터는 기본 갯수로 partition된다") {
          useCase(idToPartitionCount).get(1).contents shouldBe Fixtures.defaultPartitionedShowcase.contents
        }
      }

      When("repository에 존재하는 id가 있는 idToPartitionCount 가 주어졌을 때") {
        And("PartitionCount가 contents의 갯수보다 큰 경우") {
          val idToPartitionCount = mapOf(Fixtures.partitionableShowcase.id to 100)

          Then("Partitionable하지 않은 데이터는 변경되지 않는다") {
            useCase(idToPartitionCount).get(0).contents shouldBe Fixtures.unpartitionableShowcase.contents
          }
          Then("Partitionable한 데이터는 전체 데이터가 반환된다") {
            useCase(idToPartitionCount).get(1).contents shouldBe Fixtures.partitionableShowcase.contents
          }
          Then("Partitionable하지 않은 데이터는 footer가 있다") {
            useCase(idToPartitionCount).get(0).footer shouldNotBe null
          }
          Then("Partitionable한 데이터는 footer가 없다") {
            useCase(idToPartitionCount).get(1).footer shouldBe null
          }
        }

        And("PartitionCount가 contents의 갯수와 같은 경우") {
          val idToPartitionCount = mapOf(Fixtures.partitionableShowcase.id to Fixtures.partitionableShowcase.contents.items.size)

          Then("Partitionable하지 않은 데이터는 변경되지 않는다") {
            useCase(idToPartitionCount).get(0).contents shouldBe Fixtures.unpartitionableShowcase.contents
          }
          Then("Partitionable한 데이터는 변경되지 않는다") {
            useCase(idToPartitionCount).get(1).contents shouldBe Fixtures.partitionableShowcase.contents
          }
          Then("Partitionable하지 않은 데이터는 footer가 있다") {
            useCase(idToPartitionCount).get(0).footer shouldNotBe null
          }
          Then("Partitionable한 데이터는 footer가 없다") {
            useCase(idToPartitionCount).get(1).footer shouldBe null
          }
        }

        And("PartitionCount가 contents의 갯수보다 작은 경우") {
          val count = partitionableContents.partitionInfo.defaultCount + 1
          val idToPartitionCount = mapOf(Fixtures.partitionableShowcase.id to count)

          Then("Partitionable하지 않은 데이터는 변경되지 않는다") {
            useCase(idToPartitionCount).get(0).contents shouldBe Fixtures.unpartitionableShowcase.contents
          }
          Then("Partitionable한 데이터는 PartitionCount만큼 partition된다") {
            useCase(idToPartitionCount).get(1).contents.items shouldBe partitionableContents.items.take(count)
          }
          Then("Partitionable하지 않은 데이터는 footer가 있다") {
            useCase(idToPartitionCount).get(0).footer shouldNotBe null
          }
          Then("Partitionable한 데이터는 footer가 있다") {
            useCase(idToPartitionCount).get(1).footer shouldNotBe null
          }
        }
      }
    }

    Given("repository에 partitionable 데이터 아이템이 기본 parition 갯수보다 부족한 경우") {
      repositoryData = Result.success(listOf(Fixtures.emptyPartitionableShowcase))

      When("빈 idToPartitionCount가 주어졌을 때") {
        val idToPartitionCount: Map<String, Int> = emptyMap()

        Then("빈 값이 반환된다") {
          useCase(idToPartitionCount).get(0).contents shouldBe Fixtures.emptyPartitionableShowcase.contents
        }
        Then("footer가 없다") {
          useCase(idToPartitionCount).get(0).footer shouldBe null
        }
      }

      When("repository에 존재하는 id가 포함된 idToPartitionCount가 주어졌을 때") {
        val idToPartitionCount = mapOf(Fixtures.partitionableShowcase.id to 5)

        Then("빈 값이 반환된다") {
          useCase(idToPartitionCount).get(0).contents shouldBe Fixtures.emptyPartitionableShowcase.contents
        }
        Then("footer가 없다") {
          useCase(idToPartitionCount).get(0).footer shouldBe null
        }
      }
    }
  }

  private fun Result<List<Showcase>>.get(index: Int): Showcase {
    return this.getOrDefault(emptyList()).get(index)
  }
}