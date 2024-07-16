package com.example.task.data.api.retrofit

import com.example.task.data.api.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun getCategoryMeals(@Query("c") categoryName: String?): MealsResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") mealId: String?): Flow<MealDetailsResponse>

}