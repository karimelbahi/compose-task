package com.example.task.presentation.ui.category

import com.example.task.data.api.model.Meal


data class CategoriesState(
    val loading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val meals: List<Meal> = emptyList(),
)
