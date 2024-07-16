package com.example.task.domain.usecases

import com.example.task.data.api.model.MealDetailsResponse
import com.example.task.domain.repo.MealDetailsRepo
import com.example.task.presentation.ui.mealdetails.MealDetailsUseCase
import com.example.task.presentation.utils.Resource
import com.example.task.presentation.utils.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealDetailsUseCaseImpl @Inject constructor(
    private val repository: MealDetailsRepo
) : MealDetailsUseCase {

    override fun getMealDetails(mealId: String): Flow<Resource<*>> {
        return repository.getMealDetails(mealId).map {
            if (it.status == Status.SUCCESS) {
                Resource.success((it.data as MealDetailsResponse).meals[0])
            } else it
        }
    }
}