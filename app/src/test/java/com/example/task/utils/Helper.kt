package com.example.task.utils

import com.example.task.data.api.model.Category
import com.example.task.data.api.model.Meal
import com.example.task.data.api.model.MealDetails

val fakeCategoryList = arrayListOf(
    Category(
        "001",
        "Fiction Books",
        "https://example.com/images/fiction-books.jpg",
        "A category for fiction books including novels, short stories, and more."
    ),
    Category(
        "002",
        "Movies",
        "https://example.com/images/movies.jpg",
        "A category for movies including action, comedy, drama, and more."
    ),
    Category(
        "003",
        "Outdoor Gear",
        "https://example.com/images/outdoor-gear.jpg",
        "A category for outdoor gear including tents, backpacks, and more."
    )
)

val fakeMeals = arrayListOf(
    Meal("1", "Spaghetti Bolognese", "https://www.example.com/spaghetti_bolognese.jpg"),
    Meal("2", "Chicken Teriyaki", "https://www.example.com/chicken_teriyaki.jpg"),
    Meal("3", "Beef Stroganoff", "https://www.example.com/beef_stroganoff.jpg")
)

val fakeMealDetails = MealDetails(strInstructions = "Instructions for Meal 1", strMeal = "Meal 1")

const val fakeCategoryName = "Beef"

const val fakeMealID = "52772"

const val errorMessage = "Error message"
