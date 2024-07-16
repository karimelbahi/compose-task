package com.example.task.presentation.ui.mealdetails

import com.example.task.data.api.model.MealDetails


data class MealDetailsState(
    val loading: Boolean = false,
    val successful: Boolean = false,
    val errorMessage: String? = null,
    val mealDetails: MealDetails? = null
)
