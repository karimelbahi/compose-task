package com.feature.category.data.repo

import com.example.common.Resource
import com.feature.category.data.api.retrofit.WebService
import com.example.common.base.BaseRepo
import com.feature.category.domain.repo.CategoryMealsRepo
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