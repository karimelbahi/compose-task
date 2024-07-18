package com.example.common.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object HomeScreens : Screens()

    @Serializable
    data class CategoriesScreens(val categoryName: String, val categoryUrl: String) : Screens()
}