package com.feature.home.data.repo

import com.example.common.Resource
import com.example.common.base.BaseRepo
import com.feature.home.data.api.retrofit.WebService
import com.feature.home.domain.repo.HomeRepo
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