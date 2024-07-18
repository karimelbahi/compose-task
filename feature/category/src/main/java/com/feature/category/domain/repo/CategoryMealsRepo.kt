package com.feature.category.domain.repo


import com.example.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface CategoryMealsRepo {
    fun getCategoryMeals(categoryName: String): Flow<Resource<*>>
}