package com.example.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.common.util.Screens
import com.feature.category.presentation.navigation.categoryScreen
import com.feature.home.presentation.navigation.homeScreen

private const val TRANSITION_DURATION = 400

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreens,
        enterTransition = {
            fadeIn(
                animationSpec = tween(TRANSITION_DURATION)
            )
        }, exitTransition = {
            fadeOut(
                animationSpec = tween(TRANSITION_DURATION)
            )
        }
    ) {
        homeScreen(navController)
        categoryScreen(navController)
    }
}

