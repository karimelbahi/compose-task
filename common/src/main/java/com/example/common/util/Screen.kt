package com.example.common.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data class CategoriesScreen(val categoryName: String, val categoryUrl: String) : Screen()
}