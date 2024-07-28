package data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    @SerialName( "categories")
    var categories: List<Category>
)

@Serializable
data class Category(
    @SerialName("idCategory")
    var categoryID: String,

    @SerialName("strCategory")
    var categoryName: String,

    @SerialName("strCategoryThumb")
    var categoryUrl: String,

    @SerialName("strCategoryDescription")
    var categoryDescription: String
)