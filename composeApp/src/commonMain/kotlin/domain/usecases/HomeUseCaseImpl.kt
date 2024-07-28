package domain.usecases

import common.utils.DataState
import data.api.model.CategoriesResponse
import data.api.model.MealsResponse
import domain.repo.HomeRepo
import kotlinx.coroutines.flow.Flow

import presentation.home.HomeUseCase

class HomeUseCaseImpl(private val repository: HomeRepo) : HomeUseCase {

    override suspend fun getCategories(): Flow<DataState<CategoriesResponse>> {
        return repository.getCategories()
    }

    override suspend fun getMeals(categoryName: String): Flow<DataState<MealsResponse>> {
        return repository.getMeals(categoryName)
    }
}