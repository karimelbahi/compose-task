package com.example.task.presentation.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.data.api.model.Meal
import com.example.task.presentation.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMealsViewModel @Inject constructor(
    private val categoryMealsUseCase: CategoryMealsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesState())
    val state  = _state as StateFlow<CategoriesState>

    fun onEvent(intent: CategoriesScreenIntent) {
        when (intent) {
            is CategoriesScreenIntent.GetCategoryMeals -> getCategoryMeals(intent.searchKeyword)
        }
    }

    fun getCategoryMeals(categoryName: String) {

        viewModelScope.launch(Dispatchers.IO) {
            categoryMealsUseCase.getCategoryMeals(categoryName).onEach { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _state.update { it.copy(loading = true) }
                    }

                    Status.SUCCESS -> {
                        _state.update {it.copy(loading = false, isSuccess = true, meals = result.data as List<Meal>)}
                    }

                    Status.ERROR -> {
                        _state.update { it.copy(loading = false, isSuccess = false) }
                    }

                }
            }.launchIn(viewModelScope)
        }
    }
}