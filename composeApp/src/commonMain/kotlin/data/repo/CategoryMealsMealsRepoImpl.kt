package data.repo

import common.utils.DataState
import data.api.model.MealsResponse
import data.utils.BASE_URL
import domain.repo.CategoryMealsRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CategoryMealsMealsRepoImpl(private val httpClient: HttpClient) : CategoryMealsRepo {

    override suspend fun getCategoryMeals(categoryName: String): Flow<DataState<MealsResponse>> =
        flow {
            emit(DataState.Loading)
            try {
                val response = httpClient.get(BASE_URL + "filter.php") {
                    parameter("c", categoryName)
                }.body<MealsResponse>()
                emit(DataState.Success(response))
            } catch (e: Exception) {
                emit(DataState.Error(e.message ?: "Unknown error"))
            }
        }

}