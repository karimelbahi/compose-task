package data.repo

import common.utils.DataState
import data.api.model.CategoriesResponse
import data.api.model.MealsResponse
import data.utils.BASE_URL
import domain.repo.HomeRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HomeRepoImpl(private val httpClient: HttpClient) : HomeRepo {

    override suspend fun getCategories(): Flow<DataState<CategoriesResponse>> = flow {
        try {
            emit(DataState.Loading)
            val response = httpClient.get(BASE_URL + "categories.php")
                .body<CategoriesResponse>()
            emit(DataState.Success(response))

        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown error"))
        }
    }


    override suspend fun getMeals(categoryName: String): Flow<DataState<MealsResponse>> = flow {

        try {
            emit(DataState.Loading)
            val response = api.httpClient.get(BASE_URL + "filter.php") {
                parameter("c", categoryName)
            }.body<MealsResponse>()
            emit(DataState.Success(response))

        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown error"))

        }
    }

}