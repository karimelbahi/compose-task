package com.example.task.presentation.ui.home

import com.example.task.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getCategories(): Flow<Resource<*>>
    fun getMeals(categoryName: String): Flow<Resource<*>>
}