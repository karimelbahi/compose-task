package presentation.category

import common.utils.DataState
import data.api.model.MealsResponse
import kotlinx.coroutines.flow.Flow

interface CategoryMealsUseCase {
    suspend fun getCategoryMeals(categoryName: String): Flow<DataState<MealsResponse>>

}