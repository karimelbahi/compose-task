package com.feature.home.data.api.retrofit

import com.feature.home.data.api.model.CategoriesResponse
import com.feature.home.data.api.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun getCategoryMeals(@Query("c") categoryName: String?): MealsResponse

}