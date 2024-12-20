package com.example.task.presentation.ui.category

import kotlinx.serialization.Serializable

@Serializable
sealed interface CategoriesScreenIntent {
    @Serializable
    data class GetCategoryMeals(val searchKeyword: String) : CategoriesScreenIntent
}
