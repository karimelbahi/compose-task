package com.example.task.data.repo

import com.example.task.data.api.retrofit.WebService
import com.example.task.data.repo.base.BaseRepo
import com.example.task.domain.repo.CategoryMealsRepo
import com.example.task.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CategoryMealsMealsRepoImpl @Inject constructor(
    private val webService: WebService
) : BaseRepo(), CategoryMealsRepo {

    override fun getCategoryMeals(categoryName: String): Flow<Resource<*>> =
        loadFromApi(true) { (webService::getCategoryMeals)(categoryName) }

}