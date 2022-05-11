package com.dreamhomefurniturecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dreamhomefurniturecompose.ui.screens.DetailScreen
import com.dreamhomefurniturecompose.ui.screens.MainScreen
import com.dreamhomefurniturecompose.ui.theme.DreamHomeTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class Destinations(val route: String) {
    object MainScreen: Destinations("Mainscreen")
    object DetailScreen: Destinations("DetailScreen/{id}") {
        /**
         * Pass article ID to DetailScreen.
         */
        fun createRoute(id: String):String {
            return "DetailScreen/$id"
        }

        const val ArgId = "id"
    }
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * set the root composable
         */
        setContent {
            DreamHomeTheme {
                ScreenDispatcher()
            }
        }
    }
}


@Composable
fun ScreenDispatcher() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.MainScreen.route) {
        composable(route = Destinations.MainScreen.route) {
            MainScreen(
                onNavClick = { id ->
                    navController.navigate(Destinations.DetailScreen.createRoute(id))
                }
            )
        }
        composable(route = Destinations.DetailScreen.route){
            DetailScreen(
                onNavClick = { navController.popBackStack() }
            )
        }
    }
}