package data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealsResponse(
    @SerialName("meals")
    val meals: List<Meal>
)

@Serializable
data class Meal(
    @SerialName("idMeal")
    val mealId: String,
    @SerialName("strMeal")
    val mealName: String,
    @SerialName("strMealThumb")
    val mealUrl: String
)