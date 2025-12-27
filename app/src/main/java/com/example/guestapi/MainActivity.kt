package com.example.guestapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.guestapi.ui.theme.BukuOnlineTheme
import com.example.guestapi.view.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BukuOnlineTheme {
                BukuOnlineApp()
            }
        }
    }
}

@Composable
fun BukuOnlineApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // Login Screen
        composable("login") {
            LoginScreen(
                onLoginSuccess = { role ->
                    if (role == "admin") {
                        navController.navigate("admin_home") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        navController.navigate("user_home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        // Register Screen
        composable("register") {
            RegisterScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.popBackStack() // Go back to login
                }
            )
        }

        // Admin Home Screen
        composable("admin_home") {
            AdminHomeScreen(
                onNavigateToAdd = {
                    navController.navigate("add_book")
                },
                onNavigateToEdit = { bookId ->
                    navController.navigate("edit_book/$bookId")
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Add Book Screen
        composable("add_book") {
            AddBookScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Edit Book Screen
        composable(
            route = "edit_book/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            EditBookScreen(
                bookId = bookId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // User Home Screen
        composable("user_home") {
            UserHomeScreen(
                onNavigateToRead = { bookId ->
                    navController.navigate("read_book/$bookId")
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Read Book Screen
        composable(
            route = "read_book/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            ReadBookScreen(
                bookId = bookId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
