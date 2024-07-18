package com.feature.home.domain.repo

import com.example.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface HomeRepo {
    fun getCategories(): Flow<Resource<*>>
    fun getMeals(categoryName: String): Flow<Resource<*>>
}