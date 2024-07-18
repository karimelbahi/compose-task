package com.feature.home.presentation

import com.feature.home.data.api.model.Category
import com.feature.home.data.api.model.Meal


data class HomeState(
    val loading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val meals: List<Meal> = emptyList(),
    val categories: List<Category> = emptyList()
)
