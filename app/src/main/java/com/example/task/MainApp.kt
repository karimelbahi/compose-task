package com.example.task

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.NavGraph

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavGraph(
        navController = navController,
    )
}