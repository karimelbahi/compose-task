package com.feature.category.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.design.component.components.LoadingDialog
import com.example.design.component.components.MyImageWithCoil
import com.example.design.component.components.MyToolbarArrowWithTitle
import com.example.design.component.utils.MainMargin
import com.feature.category.R
import com.feature.category.presentation.components.CategoryMealComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CategoriesScreen(
    categoryName: String,
    categoryUrl: String,
    navController: NavController,
    state: StateFlow<CategoriesState>,
    onEvent: (CategoriesScreenIntent) -> Unit,
) {

    val stateValue = state.collectAsStateWithLifecycle().value
    val scrollState = rememberScrollState()

    LoadingDialog(stateValue.loading)
    LaunchedEffect(Unit) {
        onEvent(CategoriesScreenIntent.GetCategoryMeals(categoryName))
    }
    if (stateValue.isSuccess || LocalInspectionMode.current) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(MainMargin),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MyToolbarArrowWithTitle(
                stringResource(R.string.beef_meals_tile),
                icon = R.drawable.ic_baseline_arrow_back_24,
                titleContentAlignment = Alignment.TopStart,
                titleStyle = TextStyle(
                    fontWeight = FontWeight(400),
                    fontSize = 17.sp
                ),
                onclickBack = { navController.popBackStack() })

            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .background(Color(0xfff8f8f8)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.category),
                        style = TextStyle(fontWeight = FontWeight.Light, fontSize = 16.sp)
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = categoryName + " " + stringResource(id = R.string.meal),
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                MyImageWithCoil(
                    modifier = Modifier.size(104.dp),
                    imageUrl = categoryUrl,
                    contentScale = ContentScale.FillBounds
                )
            }

            for (meal in stateValue.meals) {
                CategoryMealComponent(meal = meal)
            }
        }
    }
}


@Preview(name = "Full Preview", showBackground = true)
@Composable
private fun CategoriesScreenPreview() {
    CategoriesScreen(
        categoryName = "",
        categoryUrl = "https://duckduckgo.com/?q=ac",
        navController = rememberNavController(),
        state = MutableStateFlow(CategoriesState())
    ) {}
}