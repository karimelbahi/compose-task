package com.example.task.presentation.ui.category

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.task.presentation.ui.home.HomeScreen
import com.example.task.presentation.ui.home.HomeState
import com.example.task.utils.fakeCategoryList
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoriesScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assert_loading_state_dialog_IsShowed() {
        val mockHomeState =
            MutableStateFlow(
                HomeState(
                    loading = false,
                    isSuccess = true,
                    categories = fakeCategoryList,
                    meals = emptyList(),
                ),
            )

        val mockState =
            MutableStateFlow(
                CategoriesState(
                    loading = true,
                    isSuccess = false,
                    meals = emptyList(),
                ),
            )

        with(composeTestRule) {
            setContent {
                val navController = rememberNavController()
                HomeScreen(navController, mockHomeState)
            }

            onNodeWithText("Beef").assertExists()
//            delay(DELAY_6000_SEC)
        }

//        with(composeTestRule) {
//            setContent {
//                val navController = rememberNavController()
//                CategoriesScreen(
//                    categoryName = categoryName,
//                    categoryUrl = "",
//                    navController = navController,
//                    state = mockState,
//                    onEvent = { },
//                )
//            }
//            onNodeWithTag(LOADING_DIALOG_TAG).assertExists()
//            onNodeWithText("Beef").performClick()
//            delay(DELAY_6000_SEC)
//        }
    }

    @Test
    fun assert_success_state_dialog_IsNotShowed() {
    }

    // TODO:  need to enhance it to use children inside the row
    @Test
    fun assert_success_state_categories_list_IsShowed() {
    }

    // TODO:  need to enhance it to use children inside the column
    @Test
    fun assert_success_state_meals_list_IsShowed() {
    }
}
