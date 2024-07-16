package com.example.task.domain.repo

import com.example.task.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface CategoryMealsRepo {
    fun getCategoryMeals(categoryName:String): Flow<Resource<*>>
}