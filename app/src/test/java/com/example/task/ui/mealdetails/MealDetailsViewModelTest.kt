package com.example.task.ui.mealdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.task.presentation.ui.mealdetails.MealDetailsUseCase
import com.example.task.presentation.ui.mealdetails.MealDetailsViewModel
import com.example.task.utils.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MealDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var mealDetailsViewModel: MealDetailsViewModel
    private val mealDetailsUseCase: MealDetailsUseCase = mock(MealDetailsUseCase::class.java)

    @Before
    fun setUp() {
        mealDetailsViewModel = MealDetailsViewModel(mealDetailsUseCase)
    }

    @Test
    fun getMealDetails_load_emitLoadState(): Unit = runTest {


    }

    @Test
    fun getMealDetails_success_emitSuccessStateWithMealDetails(): Unit = runTest {


    }

    @Test
    fun getMealDetails__failure_emitErrorState(): Unit = runTest {


    }
}