package com.example.task.presentation.ui.mealdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.data.api.model.MealDetails
import com.example.task.presentation.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val mealDetailsUseCase: MealDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MealDetailsState())
    val state = _state

    fun getMealDetails(mealId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mealDetailsUseCase.getMealDetails(mealId).onEach { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _state.update { it.copy(loading = true) }
                    }
                    Status.SUCCESS -> {
                        _state.update { it.copy(loading = false, mealDetails = result.data as MealDetails?) }
                    }
                    Status.ERROR -> {
                        _state.update { it.copy(loading = false) }
                    }
                }

            }.launchIn(viewModelScope)
        }
    }

    fun getMealDescription(meal: MealDetails): Pair<String, String> {
        val sbIngredient = StringBuilder()
        val sbMeasure = StringBuilder()
        if (!meal.strIngredient1.isNullOrEmpty()) {
            sbIngredient.append("${meal.strIngredient1} ")
            sbMeasure.append("${meal.strMeasure1} ")
        }

        if (!meal.strIngredient2.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient2} ")
            sbMeasure.append(", ${meal.strMeasure2} ")
        }

        if (!meal.strIngredient3.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient3} ")
            sbMeasure.append(", ${meal.strMeasure3} ")
        }

        if (!meal.strIngredient4.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient4} ")
            sbMeasure.append(", ${meal.strMeasure4} ")
        }

        if (!meal.strIngredient5.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient5} ")
            sbMeasure.append(", ${meal.strMeasure5} ")
        }

        if (!meal.strIngredient6.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient6} ")
            sbMeasure.append(", ${meal.strMeasure6} ")
        }

        if (!meal.strIngredient7.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient7} ")
            sbMeasure.append(", ${meal.strMeasure7} ")
        }

        if (
            !meal.strIngredient8.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient8} ")
            sbMeasure.append(", ${meal.strMeasure8} ")
        }

        if (!meal.strIngredient9.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient9} ")
            sbMeasure.append(", ${meal.strMeasure9} ")
        }

        if (!meal.strIngredient10.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient10} ")
            sbMeasure.append(", ${meal.strMeasure10} ")
        }

        if (!meal.strIngredient11.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient11} ")
            sbMeasure.append(", ${meal.strMeasure11} ")
        }

        if (
            !meal.strIngredient12.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient12} ")
            sbMeasure.append(", ${meal.strMeasure12} ")
        }

        if (!meal.strIngredient13.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient13} ")
            sbMeasure.append(", ${meal.strMeasure13} ")
        }

        if (
            !meal.strIngredient14.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient14} ")
            sbMeasure.append(", ${meal.strMeasure14} ")
        }

        if (!meal.strIngredient15.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient15} ")
            sbMeasure.append(", ${meal.strMeasure15} ")
        }

        if (!meal.strIngredient16.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient16} ")
            sbMeasure.append(", ${meal.strMeasure16} ")
        }

        if (!meal.strIngredient17.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient17} ")
            sbMeasure.append(", ${meal.strMeasure17} ")
        }

        if (!meal.strIngredient18.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient18} ")
            sbMeasure.append(", ${meal.strMeasure18} ")
        }

        if (!meal.strIngredient19.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient19} ")
            sbMeasure.append(", ${meal.strMeasure19} ")
        }

        if (!meal.strIngredient20.isNullOrEmpty()) {
            sbIngredient.append(", ${meal.strIngredient20} ")
            sbMeasure.append(", ${meal.strMeasure20} ")
        }

        return Pair(sbIngredient.toString(), sbMeasure.toString())
    }

}