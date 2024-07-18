package com.feature.category.presentation

import com.feature.category.data.api.model.Meal


data class CategoriesState(
    val loading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val meals: List<Meal> = emptyList(),
)