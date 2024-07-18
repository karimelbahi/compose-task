package com.feature.home.domain.usecases

import com.example.common.Resource
import com.example.common.Status
import com.feature.home.data.api.model.CategoriesResponse
import com.feature.home.data.api.model.MealsResponse
import com.feature.home.domain.repo.HomeRepo
import com.feature.home.presentation.HomeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repository: HomeRepo
) : HomeUseCase {
    override fun getCategories(): Flow<Resource<*>> {
        return repository.getCategories().map { it ->
            if (it.status == Status.SUCCESS) {
                Resource.success((it.data as CategoriesResponse).categories)
            } else it
        }
    }

    override fun getMeals(categoryName: String): Flow<Resource<*>> {
        return repository.getMeals(categoryName).map { it ->
            if (it.status == Status.SUCCESS) {
                Resource.success((it.data as MealsResponse).meals)
            } else it
        }
    }
}