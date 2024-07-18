package com.feature.category.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CategoriesScreenIntent {
    @Serializable
    data class GetCategoryMeals(val searchKeyword: String) : CategoriesScreenIntent
}
