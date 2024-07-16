package com.example.task.data.repo

import com.example.task.data.api.retrofit.WebService
import com.example.task.data.repo.base.BaseRepo
import com.example.task.domain.repo.MealDetailsRepo
import com.example.task.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MealDetailsRepoImpl @Inject constructor(
    private val webService: WebService
) : BaseRepo(), MealDetailsRepo {

    override fun getMealDetails(mealId: String): Flow<Resource<*>> =
        loadFromApi { (webService::getMealDetails)(mealId) }


}