package com.example.task.utils

import com.example.task.data.api.model.Category
import com.example.task.data.api.model.Meal

const val DELAY_3000_SEC = 3000L
const val DELAY_6000_SEC = 6000L

val fakeCategoryList = arrayListOf(
    Category(
        categoryID = "001",
        categoryName = "Beef",
        categoryUrl = "https://example.com/images/fiction-books.jpg",
        categoryDescription = "A category for fiction books including novels, short stories, and more."
    ),
    Category(
        categoryID = "002",
        categoryName = "Chicken",
        categoryUrl = "https://example.com/images/movies.jpg",
        categoryDescription = "A category for movies including action, comedy, drama, and more."
    ),
    Category(
        categoryID = "003",
        categoryName = "Dessert",
        categoryUrl = "https://example.com/images/outdoor-gear.jpg",
        categoryDescription = "A category for outdoor gear including tents, backpacks, and more."
    )
)

val fakeMeals = arrayListOf(
    Meal("1", "Spaghetti Bolognese", "https://www.example.com/spaghetti_bolognese.jpg"),
    Meal("2", "Chicken Teriyaki", "https://www.example.com/chicken_teriyaki.jpg"),
    Meal("3", "Beef Stroganoff", "https://www.example.com/beef_stroganoff.jpg")
)

