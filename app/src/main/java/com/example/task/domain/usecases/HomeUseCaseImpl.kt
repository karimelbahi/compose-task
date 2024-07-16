package com.example.task.domain.usecases

import com.example.task.data.api.model.CategoriesResponse
import com.example.task.data.api.model.MealsResponse
import com.example.task.domain.repo.HomeRepo
import com.example.task.presentation.ui.home.HomeUseCase
import com.example.task.presentation.utils.Resource
import com.example.task.presentation.utils.Status
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