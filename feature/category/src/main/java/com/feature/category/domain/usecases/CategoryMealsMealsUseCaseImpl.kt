package com.feature.category.domain.usecases

import com.example.common.Resource
import com.example.common.Status
import com.feature.category.data.api.model.MealsResponse
import com.feature.category.domain.repo.CategoryMealsRepo
import com.feature.category.presentation.CategoryMealsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryMealsMealsUseCaseImpl @Inject constructor(
    private val repository: CategoryMealsRepo
) : CategoryMealsUseCase {

    override fun getCategoryMeals(categoryName: String): Flow<Resource<*>> {
        return repository.getCategoryMeals(categoryName).map {
            if (it.status == Status.SUCCESS) {
                Resource.success((it.data as MealsResponse).meals)
            } else it
        }
    }
}