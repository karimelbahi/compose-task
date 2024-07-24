package presentation.home

import data.api.model.Category
import data.api.model.Meal


data class HomeState(
    val loading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val navigateToDetails: Boolean = false,
    val meals: List<Meal> = emptyList(),
    val categories: List<Category> = emptyList()
)
