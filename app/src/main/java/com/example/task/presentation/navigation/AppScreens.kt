package com.example.task.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class AppScreens {
    @Serializable
    data object HomeScreen : AppScreens()

    @Serializable
    data class CategoriesScreen(val categoryName: String, val categoryUrl: String) : AppScreens()
}