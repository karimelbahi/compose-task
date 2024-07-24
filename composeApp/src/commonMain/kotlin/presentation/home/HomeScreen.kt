package presentation.home

import LoadingDialog
import VerticalGrid
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cmp.composeapp.generated.resources.Res
import cmp.composeapp.generated.resources.beef_meals
import cmp.composeapp.generated.resources.categories
import common.MainMargin
import ignoreHorizontalParentPadding
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.category.CategoriesScreen
import presentation.category.CategoryMealsViewModel

class HomeScreen() : Screen {

    @Composable
    override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
        val viewModel = HomeViewModel()
    val state = viewModel.state
    val stateValue = state.collectAsState().value
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
            HeaderSectionComponent(stringResource(Res.string.categories))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .ignoreHorizontalParentPadding(MainMargin),
            ) {
                item { Spacer(modifier = Modifier.width(8.dp)) }
                items(stateValue.categories) {
                    CategoryComponent(it) {
                        navigator.push(CategoriesScreen(it.categoryName, it.categoryUrl , CategoryMealsViewModel(it.categoryName).state))
                    }
                }
                item { Spacer(modifier = Modifier.width(8.dp)) }
            }

            HeaderSectionComponent(stringResource(Res.string.beef_meals))
            val composableList: List<@Composable () -> Unit> = stateValue.meals.map { meal ->
                { MealComponent(meal) }
            }
            VerticalGrid(composableList = composableList, itemsPerRow = 2)
        }
}
}


@Preview()
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}