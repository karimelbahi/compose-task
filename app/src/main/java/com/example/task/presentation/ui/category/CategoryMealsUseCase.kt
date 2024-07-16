package com.example.task.presentation.ui.category

import com.example.task.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryMealsUseCase {
    fun getCategoryMeals(categoryName: String): Flow<Resource<*>>
}