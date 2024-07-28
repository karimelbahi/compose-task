package presentation.category

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import common.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryMealsViewModel(private val categoryMealsUseCase: CategoryMealsUseCase) : ScreenModel {

    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.asStateFlow().stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CategoriesState())

    fun getCategoryMeals(categoryName: String) {
        screenModelScope.launch(Dispatchers.IO) {
            categoryMealsUseCase.getCategoryMeals(categoryName).collect { result ->
                when (result) {
                    DataState.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update { it.copy(loading = false, isSuccess = true, meals = result.data.meals) }
                        }
                    }

                    is DataState.Error -> {
                        _state.update { it.copy(loading = false, errorMessage = result.error) }
                    }
                }
            }
        }
    }
}