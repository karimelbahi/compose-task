package com.feature.category.presentation

import com.example.common.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryMealsUseCase {
    fun getCategoryMeals(categoryName: String): Flow<Resource<*>>
}