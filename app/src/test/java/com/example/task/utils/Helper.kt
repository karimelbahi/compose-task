package com.example.task.utils

import com.example.task.data.api.model.Meal

val fakeMeals =
    arrayListOf(
        Meal("1", "Spaghetti Bolognese", "https://www.example.com/spaghetti_bolognese.jpg"),
        Meal("2", "Chicken Teriyaki", "https://www.example.com/chicken_teriyaki.jpg"),
        Meal("3", "Beef Stroganoff", "https://www.example.com/beef_stroganoff.jpg"),
    )

const val FAKE_CATEGORY_NAME = "Beef"

const val ERROR_MESSAGE = "Error message"
