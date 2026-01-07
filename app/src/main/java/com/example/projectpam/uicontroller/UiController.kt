package com.example.projectpam.uicontroller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// Class controller untuk mengatur navigasi atau logika UI global
class UiController(
    val navController: NavHostController
) {
    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

@Composable
fun rememberUiController(
    navController: NavHostController = rememberNavController()
): UiController {
    return UiController(navController)
}

