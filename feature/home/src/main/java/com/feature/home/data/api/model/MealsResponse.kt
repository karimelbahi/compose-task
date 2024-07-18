package com.feature.home.data.api.model

import com.google.gson.annotations.SerializedName


data class MealsResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)

data class Meal(
    @SerializedName("idMeal")
    val mealId: String,
    @SerializedName("strMeal")
    val mealName: String,
    @SerializedName("strMealThumb")
    val mealUrl: String
)