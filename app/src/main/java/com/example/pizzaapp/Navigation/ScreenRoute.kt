package com.example.pizzaapp.Navigation

sealed class ScreenRoute(val route: String) {

    object Home : ScreenRoute(route = "home")
    object Profile : ScreenRoute(route = "profile")
    object Favourite : ScreenRoute(route = "favourite")
    object Settings : ScreenRoute(route = "settings")
}