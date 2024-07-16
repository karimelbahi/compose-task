package com.example.task.domain.usecases

import com.example.task.data.api.model.MealsResponse
import com.example.task.domain.repo.CategoryMealsRepo
import com.example.task.presentation.ui.category.CategoryMealsUseCase
import com.example.task.presentation.utils.Resource
import com.example.task.presentation.utils.Status
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