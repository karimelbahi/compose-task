package com.feature.home.presentation

import com.example.common.Resource
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getCategories(): Flow<Resource<*>>
    fun getMeals(categoryName: String): Flow<Resource<*>>
}