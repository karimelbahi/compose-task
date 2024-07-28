package presentation.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import common.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val categoryName = "Beef"

class HomeViewModel(private val homeUseCase: HomeUseCase) : ScreenModel {

    private val _state = MutableStateFlow(HomeState())
    val state = _state as StateFlow<HomeState>

    fun getHomePage() {
        getCategories()
    }

    private fun getCategories() {
        screenModelScope.launch(Dispatchers.IO) {
            homeUseCase.getCategories().collect { result ->
                when (result) {
                    is DataState.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is DataState.Success -> {
                        _state.update { it.copy(categories = result.data.categories) }
                        getMeals()
                    }

                    is DataState.Error -> {
                        _state.update { it.copy(loading = false, errorMessage = result.error) }
                    }
                }
            }
        }
    }

    private fun getMeals() {
        screenModelScope.launch(Dispatchers.IO) {
            homeUseCase.getMeals(categoryName)
                .collect { result ->
                    when (result) {
                        is DataState.Loading -> {
                            _state.update { it.copy(loading = true) }
                        }

                        is DataState.Success -> {
                            _state.update {
                                it.copy(
                                    loading = false,
                                    isSuccess = true,
                                    meals = result.data.meals
                                )
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
