package com.example.task.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.task.presentation.ui.home.HomeState
import com.example.task.presentation.ui.home.HomeUseCase
import com.example.task.presentation.ui.home.HomeViewModel
import com.example.task.presentation.utils.Resource
import com.example.task.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Unit tests for the HomeViewModel class.
 * This class tests the behavior of the view model in various scenarios.
 */
class HomeViewModelTest {
    /**
     * JUnit rule to enforce synchronous execution of tasks on the main thread for testing purposes.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * JUnit rule that provides a test coroutine dispatcher for testing coroutines.
     */
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    /**
     * Instance of the HomeViewModel under test.
     */
    private lateinit var homeViewModel: HomeViewModel

    /**
     * Mock object for the HomeUseCase dependency.
     */
    private val homeUseCase: HomeUseCase = mock(HomeUseCase::class.java)

    /**
     * Sets up the test environment before each test.
     * - Sets the main dispatcher to the test dispatcher provided by mainCoroutineRule.
     * - Creates an instance of HomeViewModel with the mocked use case.
     */
    @Before
    fun setUp() {
        Dispatchers.setMain(mainCoroutineRule.testDispatcher)
        homeViewModel = HomeViewModel(homeUseCase)
    }

    /**
     * Resets the main dispatcher after each test.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getCategories_load_emitLoadState(): Unit =
        runTest {
            whenever(homeUseCase.getCategories()).thenReturn(
                flowOf(
                    Resource.loading(
                        data = true,
                    ),
                ),
            )

            homeViewModel.getHomePage()

            val stateList = mutableListOf<HomeState>()
            val job =
                launch {
                    homeViewModel.state
                        .onEach {
                            homeViewModel.state.toList(stateList)
                        }.collect()
                }

            advanceUntilIdle()

            val latestState = stateList[0]

            TestCase.assertEquals(true, latestState.loading)
            TestCase.assertEquals(false, latestState.isSuccess)
            TestCase.assertEquals(null, latestState.errorMessage)
            assertTrue(latestState.meals.isEmpty())

            job.cancel()
        }

    // MethodName_StateUnderTest_ExpectedBehavior
    @Test
    fun getCategories_success_emitSuccessStateWithCategoryAndMealList(): Unit =
        runTest {
/*        whenever(homeUseCase.getCategories().first()).thenReturn(Resource.success(fakeCategoryList))
        whenever(homeUseCase.getMeals(fakeCategoryName).first()).thenReturn(
            Resource.success(
                fakeMeals
            )
        )

        homeViewModel.getHomePage()

        val stateList = mutableListOf<HomeState>()
        val job = launch {
            homeViewModel.state.onEach {
                homeViewModel.state.toList(stateList)
            }.collect()
        }

        advanceUntilIdle()

        val latestState = stateList[0]

        TestCase.assertEquals(true, latestState.loading)
        TestCase.assertEquals(false, latestState.isSuccess)
        TestCase.assertEquals(null, latestState.errorMessage)
        assertTrue(latestState.meals.isEmpty())

        job.cancel()*/
        }

    @Test
    fun getCategories_failure_returnsError(): Unit =
        runTest {
        /*      whenever(homeUseCase.getCategories()).thenReturn(
                  flowOf(
                      Resource.error(
                          msg = errorMessage,
                          data = null
                      )
                  )
              )

              homeViewModel.getHomePage()

              val latestState = homeViewModel.state.first()

              advanceUntilIdle()

              TestCase.assertEquals(false, latestState.loading)
              TestCase.assertEquals(false, latestState.isSuccess)
              assertTrue(latestState.meals.isEmpty())*/
        }
}
