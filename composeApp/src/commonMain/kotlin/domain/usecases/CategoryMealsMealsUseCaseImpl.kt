package domain.usecases

import common.utils.DataState
import data.api.model.MealsResponse
import domain.repo.CategoryMealsRepo
import kotlinx.coroutines.flow.Flow
import presentation.category.CategoryMealsUseCase

class CategoryMealsMealsUseCaseImpl(private val repository: CategoryMealsRepo) : CategoryMealsUseCase {

    override suspend fun getCategoryMeals(categoryName: String): Flow<DataState<MealsResponse>> {
        return repository.getCategoryMeals(categoryName)
    }

}