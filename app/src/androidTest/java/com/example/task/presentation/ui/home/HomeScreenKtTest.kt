package com.example.task.presentation.ui.home

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.task.common.component.LOADING_DIALOG_TAG
import com.example.task.presentation.ui.home.HomeScreenTestTags.LAZY_GRID
import com.example.task.presentation.ui.home.HomeScreenTestTags.LAZY_ROW
import com.example.task.presentation.ui.home.HomeScreenTestTags.LAZY_ROW_SPACE
import com.example.task.presentation.ui.home.HomeScreenTestTags.MAIN_COLUMN
import com.example.task.presentation.ui.home.components.CATEGORY_COMPONENT_LAZY_COLUMN
import com.example.task.utils.DELAY_6000_SEC
import com.example.task.utils.fakeCategoryList
import com.example.task.utils.fakeMeals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assert_loading_state_dialog_IsShowed() {
        val mockState =
            MutableStateFlow(
                HomeState(
                    loading = true,
                    isSuccess = false,
                    categories = emptyList(),
                    meals = emptyList(),
                ),
            )

        with(composeTestRule) {
            setContent {
                val navController = rememberNavController()
                HomeScreen(navController, mockState)
            }
            onNodeWithTag(LOADING_DIALOG_TAG).assertExists()
            onNodeWithText("Categories").assertDoesNotExist()
            onNodeWithText(fakeCategoryList[0].categoryName).assertDoesNotExist()
            onNodeWithText("Beef Meals").assertDoesNotExist()
            onNodeWithText(fakeMeals[0].mealName).assertDoesNotExist()
        }
    }

    @Test
    fun assert_success_state_dialog_IsNotShowed() {
        val mockState =
            MutableStateFlow(
                HomeState(
                    loading = false,
                    isSuccess = true,
                    categories = emptyList(),
                    meals = emptyList(),
                ),
            )

        with(composeTestRule) {
            setContent {
                val navController = rememberNavController()
                HomeScreen(navController, mockState)
            }

            onNodeWithTag(LOADING_DIALOG_TAG).assertDoesNotExist()
        }
    }

    // TODO:  need to enhance it to use children inside the row
    @Test
    fun assert_success_state_categories_list_IsShowed() {
        val mockState =
            MutableStateFlow(
                HomeState(
                    loading = false,
                    isSuccess = true,
                    categories = fakeCategoryList,
                    meals = emptyList(),
                ),
            )
        with(composeTestRule) {
            setContent {
                val navController = rememberNavController()
                HomeScreen(navController, mockState)
            }

            onNodeWithText("Categories").assertExists()

            onNodeWithTag(MAIN_COLUMN).assertIsDisplayed()
            onNodeWithTag(LAZY_ROW).assertIsDisplayed()
            onNodeWithTag(LAZY_GRID).assertIsNotDisplayed()
            onNodeWithTag(LAZY_ROW)
                .onChildAt(0)
                .assert(hasTestTag(LAZY_ROW_SPACE.plus(0)))
            onNodeWithTag(LAZY_ROW)
                .onChildAt(1)
                .assert(hasTestTag(CATEGORY_COMPONENT_LAZY_COLUMN.plus(fakeCategoryList[0].categoryName)))
            onNodeWithTag(LAZY_ROW)
                .onChildAt(2)
                .assert(hasTestTag(CATEGORY_COMPONENT_LAZY_COLUMN.plus(fakeCategoryList[1].categoryName)))
            onNodeWithTag(LAZY_ROW)
                .onChildAt(3)
                .assert(hasTestTag(CATEGORY_COMPONENT_LAZY_COLUMN.plus(fakeCategoryList[2].categoryName)))
            runTest {
                delay(DELAY_6000_SEC)
            }
        }
    }

    // TODO:  need to enhance it to use children inside the column
    @Test
    fun assert_success_state_meals_list_IsShowed() {
        val mockState =
            MutableStateFlow(
                HomeState(
                    loading = false,
                    isSuccess = true,
                    categories = fakeCategoryList,
                    meals = fakeMeals,
                ),
            )

        with(composeTestRule) {
            setContent {
                val navController = rememberNavController()
                HomeScreen(navController, mockState)
            }

            onNodeWithText("Beef Meals").assertExists()
            onNodeWithText(fakeMeals[0].mealName).assertExists()
            onNodeWithText(fakeMeals[1].mealName).assertExists()
            onNodeWithText(fakeMeals[2].mealName).assertExists()

            onNodeWithTag(LOADING_DIALOG_TAG).assertDoesNotExist()
        }
    }
}
