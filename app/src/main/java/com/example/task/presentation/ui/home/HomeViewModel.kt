package com.example.task.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.data.api.model.Category
import com.example.task.data.api.model.Meal
import com.example.task.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

const val categoryName = "Beef"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state as StateFlow<HomeState>

    init {
        getHomePage()
    }

    fun getHomePage() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                supervisorScope {
                    _state.update { it.copy(loading = true) }
                    val categories = async { homeUseCase.getCategories().first() }
                    val meals = async { homeUseCase.getMeals(categoryName).first() }
                    categories.join()
                    meals.join()
                    val categoriesResult = categories.await() as Resource<List<Category>>
                    val mealsResult = meals.await() as Resource<List<Meal>>
                    _state.update {
                        it.copy(
                            loading = false,
                            isSuccess = true,
                            categories = categoriesResult.data ?: emptyList(),
                            meals = mealsResult.data ?: emptyList()
                        )
                    }
                }
            } catch (_: Exception) {
            }
        }
    }
}