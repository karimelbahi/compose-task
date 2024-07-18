package com.feature.home.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.common.util.Screens
import com.feature.home.presentation.HomeScreen
import com.feature.home.presentation.HomeViewModel

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable<Screens.HomeScreens> {
        val viewModel: HomeViewModel = hiltViewModel()
        val state = viewModel.state
        HomeScreen(navController, state)
    }
}