package presentation.home

import common.utils.DataState
import data.api.model.CategoriesResponse
import data.api.model.MealsResponse
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    suspend fun getCategories(): Flow<DataState<CategoriesResponse>>
    suspend fun getMeals(categoryName: String): Flow<DataState<MealsResponse>>
}