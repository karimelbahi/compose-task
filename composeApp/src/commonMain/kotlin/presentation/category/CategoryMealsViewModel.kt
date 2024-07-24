package presentation.category

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryMealsViewModel (categoryName: String) : ViewModel() {

    private val categoryMealsRepository = CategoryMealsRepository()

    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.asStateFlow()


    init {
        getCategoryMeals(categoryName)
    }
    private fun getCategoryMeals(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(loading = true) }
            categoryMealsRepository.getCategoryMeals(categoryName).onEach { result ->
                _state.update { it.copy(loading = false, isSuccess = true , meals = result.meals) }

            }.launchIn(viewModelScope)
        }
    }
}