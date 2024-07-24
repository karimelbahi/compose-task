package presentation.home

import api.httpClient
import data.api.model.CategoriesResponse
import data.api.model.MealsResponse
import data.utils.BASE_URL
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.flow


class HomeRepository {

    private suspend fun getCategoriesApi(): CategoriesResponse {
        val response = httpClient.get(BASE_URL + "categories.php")
        return response.body()
    }

    fun getCategories() = flow {
        emit(getCategoriesApi())
    }

    private suspend fun getCategoryMealsApi(categoryName: String): MealsResponse {
        val response = httpClient.get(BASE_URL + "filter.php") {
            parameter("c", categoryName)
        }
        return response.body()
    }

    fun getCategoryMeals(categoryName: String) = flow {
        emit(getCategoryMealsApi(categoryName))
    }

}