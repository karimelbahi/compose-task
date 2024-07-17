package com.example.task.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.task.R
import com.example.task.common.component.LoadingDialog
import com.example.task.common.component.VerticalGrid
import com.example.task.common.utils.ignoreHorizontalParentPadding
import com.example.task.presentation.navigation.AppScreens
import com.example.task.presentation.ui.home.components.CategoryComponent
import com.example.task.presentation.ui.home.components.HeaderSectionComponent
import com.example.task.presentation.ui.home.components.MealComponent
import com.example.task.ui.theme.MainMargin
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeScreen(navController: NavController, state: StateFlow<HomeState>) {

    val stateValue = state.collectAsStateWithLifecycle().value
    val scrollState = rememberScrollState()

    LoadingDialog(stateValue.loading)

    if (stateValue.isSuccess || LocalInspectionMode.current)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(MainMargin),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HeaderSectionComponent(stringResource(R.string.categories_tile))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .ignoreHorizontalParentPadding(MainMargin),
            ) {
                item { Spacer(modifier = Modifier.width(8.dp)) }
                items(stateValue.categories) {
                    CategoryComponent(it) {
                        navController.navigate(
                            AppScreens.CategoriesScreen(
                                categoryName = it.categoryName,
                                categoryUrl = it.categoryUrl
                            )
                        )
                    }
                }
                item { Spacer(modifier = Modifier.width(8.dp)) }
            }

            HeaderSectionComponent(stringResource(R.string.beef_meals_tile))
            val composableList: List<@Composable () -> Unit> = stateValue.meals.map { meal ->
                { MealComponent(meal) }
            }
            VerticalGrid(composableList = composableList, itemsPerRow = 2)
        }
}


@Preview(name = "Full Preview", showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        state = MutableStateFlow(HomeState())
    )
}