package home

import data.api.model.CategoriesResponse
import data.api.model.MealsResponse
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

const val categoryName = "Beef"

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository()

    private val _state = MutableStateFlow(HomeState())
    val state = _state as StateFlow<HomeState>

    init {
        getHomePage()
    }

    private fun getHomePage() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                supervisorScope {
                    _state.update { it.copy(loading = true) }
                    val categories = async { homeRepository.getCategories().first() }
                    val meals = async { homeRepository.getCategoryMeals(categoryName).first() }
                    categories.join()
                    meals.join()
                    val categoriesResult = categories.await() as CategoriesResponse
                    val mealsResult = meals.await() as MealsResponse
                    _state.update {
                        it.copy(
                            loading = false,
                            isSuccess = true,
                            categories = categoriesResult.categories,
                            meals = mealsResult.meals
                        )
                    }
                }
            } catch (_: Exception) {
            }
        }
    }
}