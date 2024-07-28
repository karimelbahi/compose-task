package domain.repo

import common.utils.DataState
import data.api.model.MealsResponse
import kotlinx.coroutines.flow.Flow

interface CategoryMealsRepo {
    suspend fun getCategoryMeals(categoryName:String): Flow<DataState<MealsResponse>>
}