package com.feature.home.data.api.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(

    @SerializedName( "categories")
    var categories: List<Category>
)

data class Category(
    @SerializedName("idCategory")
    var categoryID: String,

    @SerializedName("strCategory")
    var categoryName: String,

    @SerializedName("strCategoryThumb")
    var categoryUrl: String,

    @SerializedName("strCategoryDescription")
    var categoryDescription: String
)