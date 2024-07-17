package com.example.task.ui.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.task.presentation.ui.category.CategoriesState
import com.example.task.presentation.ui.category.CategoryMealsUseCase
import com.example.task.presentation.ui.category.CategoryMealsViewModel
import com.example.task.presentation.utils.Resource
import com.example.task.utils.MainCoroutineRule
import com.example.task.utils.errorMessage
import com.example.task.utils.fakeCategoryName
import com.example.task.utils.fakeMeals
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
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
 * Unit tests for the CategoryMealsViewModel class.
 * This class tests the behavior of the view model in various scenarios.
 */
class CategoryMealsViewModelTest {

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
     * Instance of the CategoryMealsViewModel under test.
     */
    private lateinit var categoryMealsViewModel: CategoryMealsViewModel

    /**
     * Mock object for the CategoryMealsUseCase dependency.
     */
    private val categoryMealsUseCase: CategoryMealsUseCase = mock(CategoryMealsUseCase::class.java)


    /**
     * Sets up the test environment before each test.
     * - Sets the main dispatcher to the test dispatcher provided by mainCoroutineRule.
     * - Creates an instance of CategoryMealsViewModel with the mocked use case.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mainCoroutineRule.testDispatcher)
        categoryMealsViewModel = CategoryMealsViewModel(categoryMealsUseCase)
    }

    /**
     * Resets the main dispatcher after each test.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    /**
     * Test to verify that the view model emits a loading state when getCategoryMeals is called.
     * - Mocks the use case to return a flow of Resource.loading.
     * - Calls getCategoryMeals with a fake category name.
     * - Collects the emitted states into a list.
     * - Uses advanceUntilIdle to ensure all coroutines have finished.
     * - Asserts that the first state has loading set to true, isSuccess is false, and meals is empty.
     */
    //When_StateUnderTest_Expect_ExpectedBehavior
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when server loading expect display loader`(): Unit = runTest {
        whenever(categoryMealsUseCase.getCategoryMeals(fakeCategoryName)).thenReturn(
            flowOf(
                Resource.loading(
                    data = true
                )
            )
        )

        categoryMealsViewModel.getCategoryMeals(fakeCategoryName)

        val stateList = mutableListOf<CategoriesState>()
        val job = launch {
            categoryMealsViewModel.state.onEach {
                categoryMealsViewModel.state.toList(stateList)
            }.collect()
        }

        advanceUntilIdle()

        val latestState = stateList[0]

        assertEquals(true, latestState.loading)
        assertEquals(false, latestState.isSuccess)
        assertEquals(null, latestState.errorMessage)
        assertTrue(latestState.meals.isEmpty())

        job.cancel()
    }


    /**
     * Test to verify that the view model emits a success state when getCategoryMeals is called.
     * - Mocks the use case to return a flow of Resource.Success.
     * - Calls getCategoryMeals with a fake category name.
     * - Collects the emitted states into a list.
     * - Asserts that the first state has Success set to true,isLoading is false,isSuccess is true, and list of meals.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when server return valid data expect return category list`(): Unit = runTest {

        whenever(categoryMealsUseCase.getCategoryMeals(fakeCategoryName)).thenReturn(
            flowOf(
                Resource.success(
                    fakeMeals
                )
            )
        )

        categoryMealsViewModel.getCategoryMeals(fakeCategoryName)

        val stateList = mutableListOf<CategoriesState>()
        val job = launch {
            categoryMealsViewModel.state.onEach {
                categoryMealsViewModel.state.toList(stateList)
            }.collect()
        }

        advanceUntilIdle()

        val latestState = stateList[0]
        assertEquals(false, latestState.loading)
        assertEquals(true, latestState.isSuccess)
        assertEquals(fakeMeals, latestState.meals)
        job.cancel()
    }

    /**
     * Test to verify that the view model emits a error state when getCategoryMeals is called.
     * - Mocks the use case to return a flow of Resource.error.
     * - Calls getCategoryMeals with a fake category name.
     * - Collects the emitted states into a list.
     * - Collects the emitted state into a state.
     * - Uses advanceUntilIdle to ensure all coroutines have finished.
     * - Asserts that the first state has Error set to true,isLoading is false, isSuccess is false, and meals is empty.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when server return failure expect get error state`(): Unit = runTest {

        whenever(categoryMealsUseCase.getCategoryMeals(fakeCategoryName)).thenReturn(
            flowOf(
                Resource.error(
                    msg = errorMessage,
                    data = null
                )
            )
        )

        categoryMealsViewModel.getCategoryMeals(fakeCategoryName)

        val latestState = categoryMealsViewModel.state.first()

        advanceUntilIdle()

        assertEquals(false, latestState.loading)
        assertEquals(false, latestState.isSuccess)
        assertTrue(latestState.meals.isEmpty())
    }
}