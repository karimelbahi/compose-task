package com.feature.category.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.common.util.Screens
import com.feature.category.presentation.CategoriesScreen
import com.feature.category.presentation.CategoryMealsViewModel

fun NavGraphBuilder.categoryScreen(navController: NavController) {
    composable<Screens.CategoriesScreens> { backstackEntry ->
        val parameters = backstackEntry.toRoute<Screens.CategoriesScreens>()
        val categoryName = parameters.categoryName
        val categoryUrl = parameters.categoryUrl
        val viewModel: CategoryMealsViewModel = hiltViewModel()
        val state = viewModel.state
        CategoriesScreen(
            categoryName = categoryName,
            categoryUrl = categoryUrl,
            navController = navController,
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}