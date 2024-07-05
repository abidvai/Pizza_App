package com.example.pizzaapp

import androidx.annotation.DrawableRes
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color

data class Pizza(
    @DrawableRes val image: Int,
    val name: String,
    val description: String,
    val price: String
)

val PizzaList = listOf(
    Pizza(
        R.drawable.pizza_1,
        "Sicilian Pizza",
        "Made in Sicilian Vibe",
        "$10.45",
    ),
    Pizza(
        R.drawable.pizza_2,
        "Hawaiin Pizza",
        "Made in Hawaiin Vibe",
        "$10.45",
    ),
    Pizza(
        R.drawable.pizza_3,
        "Greek Pizza",
        "Made in Greek Vibe",
        "$10.45",
    ),
    Pizza(
        R.drawable.pizza_4,
        "Bangla Pizza",
        "Made in Deshi Vibe",
        "$10.45",
    ),
    Pizza(
        R.drawable.pizza_4,
        "Tangail Pizza",
        "Made in Tangaila Vibe",
        "$10.45",
    ),
    Pizza(
        R.drawable.pizza_4,
        "Adalot para boys Pizza",
        "Made in smp Vibe",
        "$10.45",
    )

)

