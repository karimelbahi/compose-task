package com.example.task.presentation.ui.mealdetails

import com.example.task.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MealDetailsUseCase {
    fun getMealDetails(mealId: String): Flow<Resource<*>>
}