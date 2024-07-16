package com.example.task.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.task.presentation.ui.category.CategoriesScreen
import com.example.task.presentation.ui.category.CategoryMealsViewModel
import com.example.task.presentation.ui.home.HomeScreen
import com.example.task.presentation.ui.home.HomeViewModel

fun appRoute(
    navBuilder: NavGraphBuilder,
    navController: NavHostController,
) {
    with(navBuilder) {
        composable<AppScreens.HomeScreen> {
            val viewModel: HomeViewModel = hiltViewModel()
            val state = viewModel.state
            HomeScreen(navController, state)
        }
        composable<AppScreens.CategoriesScreen> { backstackEntry ->
            val parameters = backstackEntry.toRoute<AppScreens.CategoriesScreen>()
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

}
