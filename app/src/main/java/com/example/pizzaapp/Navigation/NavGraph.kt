package com.example.pizzaapp.Navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizzaapp.HomePage
import com.example.pizzaapp.ShowPizza

@Composable
fun NavGraph() {
    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            HomePage(navController = navController)
        }
        composable(ScreenRoute.Profile.route) {
            ProfilePage(navController = navController)
        }
        composable(ScreenRoute.Favourite.route) {
            FavouritePage(navController = navController)
        }
        composable(ScreenRoute.Settings.route) {
            SettingsPage(navController = navController)
        }
    }

    }

@Composable
fun SettingsPage(navController: NavHostController) {
    Text(text = "Setting PAge")
}

@Composable
fun FavouritePage(navController: NavHostController) {
    Text(text = "Favourite PAge")
}

@Composable
fun ProfilePage(navController: NavHostController) {
    Text(text = "Profile PAge")
}
