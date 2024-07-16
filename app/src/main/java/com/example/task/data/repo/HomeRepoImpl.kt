package com.example.task.data.repo

import com.example.task.data.api.retrofit.WebService
import com.example.task.data.repo.base.BaseRepo
import com.example.task.domain.repo.HomeRepo
import com.example.task.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HomeRepoImpl @Inject constructor(
    private val webService: WebService
) : BaseRepo(), HomeRepo {

    override fun getCategories(): Flow<Resource<*>> =
        loadFromApi { (webService::getCategories)() }

    override fun getMeals(categoryName: String): Flow<Resource<*>> =
        loadFromApi { (webService::getCategoryMeals)(categoryName) }

}