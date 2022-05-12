package com.dreamhomefurniturecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dreamhomefurniturecompose.ui.screens.CompareScreen
import com.dreamhomefurniturecompose.ui.screens.DetailScreen
import com.dreamhomefurniturecompose.ui.screens.MainScreen
import com.dreamhomefurniturecompose.ui.theme.DreamHomeTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class Destinations(val route: String) {
    object MainScreen: Destinations("Mainscreen")
    object DetailScreen: Destinations("DetailScreen/{id}") {
        fun createRoute(id: String): String {
            return "DetailScreen/$id"
        }

        const val ArgId = "id"
    }
    object CompareScreen: Destinations("CompareScreen/{idOne}/{idTwo}") {
        fun createRoute(idOne: String, idTwo:String): String {
            return "CompareScreen/$idOne/$idTwo"
        }

        const val ArgIdOne = "idOne"
        const val ArgIdTwo = "idTwo"
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
                onCardNavClick = { id ->
                    navController.navigate(Destinations.DetailScreen.createRoute(id))
                },
                onCompareNavClick = { idOne, idTwo ->
                    navController.navigate(Destinations.CompareScreen.createRoute(idOne, idTwo))
                }
            )
        }
        composable(route = Destinations.DetailScreen.route) {
            DetailScreen(
                onNavClick = { navController.popBackStack() }
            )
        }
        composable(route = Destinations.CompareScreen.route) {
            CompareScreen(
                onNavClick = { id ->
                    navController.navigate(Destinations.DetailScreen.createRoute(id))
                },
                onBackNavClick = { navController.popBackStack() }
            )
        }
    }
}