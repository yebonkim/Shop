package com.example.shop.feature.home

import com.airbnb.mvrx.Success
import com.airbnb.mvrx.test.MavericksTestRule
import com.airbnb.mvrx.withState
import com.example.shop.domain.Fixtures
import com.example.shop.domain.ShowcaseRepository
import com.example.shop.domain.model.Showcase
import com.example.shop.domain.usecase.GetPartitionedShowcasesUseCase
import com.example.shop.domain.usecase.RefreshShowcaseUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

class HomeViewModelTest {
  private var repositoryData = emptyList<Showcase>()

  private val repository: ShowcaseRepository = mockk()
  private val getPartitionedShowcasesUseCase: GetPartitionedShowcasesUseCase =
    GetPartitionedShowcasesUseCase(
      showcaseRepository = repository
    )
  private val refreshShowcaseUseCase: RefreshShowcaseUseCase = RefreshShowcaseUseCase(
    showcaseRepository = repository
  )
  private lateinit var viewModel: HomeViewModel

  @Before
  fun before() {
    coEvery { repository.loadShowcases() } answers { repositoryData }
    coEvery { repository.update(any()) } answers {
      val showcases = firstArg<List<Showcase>>()
      repositoryData = showcases
    }
  }

  @Test
  fun `viewModel 생성 시에 저장소에 데이터가 없을 경우 빈 데이터를 얻는다`() {
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )

    withState(viewModel) { assertEquals(it.showcases, Success(emptyList<Showcase>())) }
  }

  @Test
  fun `viewModel 생성 시에 저장소에 데이터가 있을 경우 기본 파티션 정보에 따라 파티션 데이터를 얻는다`() {
    repositoryData = listOf(
      Fixtures.partitionableShowcase,
      Fixtures.unpartitionableShowcase,
    )

    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )

    withState(viewModel) {
      assertEquals(
        it.showcases, Success(
          listOf(
            Fixtures.defaultPartitionedShowcase,
            Fixtures.unpartitionableShowcase,
          )
        )
      )
    }
  }

  @Test
  fun `footer가 없는 showcase id로 footer 클릭 함수를 호출하면 데이터가 변경되지 않는다`() {
    repositoryData = listOf(Fixtures.unpartitionableShowcase)
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )

    viewModel.onClickFooter("INVALID ID")

    withState(viewModel) {
      assertEquals(it.showcases, Success(repositoryData))
    }
  }

  @Test
  fun `refresh footer를 가진 showcaseId로 footer 클릭 함수를 호출하면 컨텐츠 순서가 섞인다`() {
    repositoryData = listOf(Fixtures.refreshableShowcase)
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )
    val beforeRefresh =
      withState(viewModel) { it.showcases()?.firstOrNull()?.contents } ?: error("empty data")

    viewModel.onClickFooter(Fixtures.refreshableShowcase.id)

    withState(viewModel) {
      val afterRefresh = it.showcases()?.firstOrNull()?.contents ?: error("empty data")
      assertNotEquals(beforeRefresh, afterRefresh)
    }
  }

  @Test
  fun `more footer를 가졌으나 더 불러올 데이터가 없는 showcaseId로 footer 클릭 함수를 호출하면 컨텐츠가 그대로다`() {
    repositoryData = listOf(Fixtures.emptyPartitionableShowcase)
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )
    val beforeMoreLoad =
      withState(viewModel) { it.showcases()?.firstOrNull()?.contents } ?: error("empty data")

    viewModel.onClickFooter(Fixtures.emptyPartitionableShowcase.id)

    withState(viewModel) {
      val afterMoreLoad = it.showcases()?.firstOrNull()?.contents ?: error("empty data")
      assertEquals(beforeMoreLoad, afterMoreLoad)
    }
  }

  @Test
  fun `more footer를 가졌고 더 불러올 데이터가 있는 showcaseId로 footer 클릭 함수를 호출하면 데이터가 추가된다`() {
    repositoryData = listOf(Fixtures.partitionableShowcase)
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )
    val beforeMoreLoad =
      withState(viewModel) { it.showcases()?.firstOrNull()?.contents } ?: error("empty data")

    viewModel.onClickFooter(Fixtures.partitionableShowcase.id)

    withState(viewModel) {
      val afterMoreLoad = it.showcases()?.firstOrNull()?.contents ?: error("empty data")
      assert(beforeMoreLoad.items.size < afterMoreLoad.items.size)
    }
  }

  @Test
  fun `url이 null인 경우 클릭 함수를 호출하면 이펙트가 발생하지 않는다`() = runTest {
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )
    val uiEffect = viewModel.uiEffect.receiveAsFlow()
    val collectJob = launch { uiEffect.collect() }

    viewModel.onClickLink(null)

    assertEquals(withTimeoutOrNull(100L) { uiEffect.first() }, null)
    collectJob.cancel()
  }

  @Test
  fun `유효한 url이 있는 경우 클릭 함수를 호출하면 네비게이션 이펙트가 발생하지 않는다`() = runTest {
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )
    val uiEffect = viewModel.uiEffect.receiveAsFlow()
    val collectJob = launch { uiEffect.collect() }
    val url = "https://example.com"

    viewModel.onClickLink(url)

    assertEquals(uiEffect.first(), HomeUiEffect.NavigateToDetail(url))
    collectJob.cancel()
  }

  @Test
  fun `유효하지 않은 url이 있는 경우 클릭 함수를 호출하면 네비게이션 이펙트가 발생한다`() = runTest {
    viewModel = HomeViewModel(
      initialState = HomeUiState(),
      getPartitionedShowcasesUseCase = getPartitionedShowcasesUseCase,
      refreshShowcaseUseCase = refreshShowcaseUseCase
    )
    val uiEffect = viewModel.uiEffect.receiveAsFlow()
    val collectJob = launch { uiEffect.collect() }
    val url = "Invalid Url"

    viewModel.onClickLink(url)

    assertEquals(withTimeoutOrNull(100L) { uiEffect.first() }, null)
    collectJob.cancel()
  }

  companion object {
    @JvmField
    @ClassRule
    val mvrxTestRule = MavericksTestRule()
  }
}