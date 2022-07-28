package com.example.easylists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easylists.model.Screens
import com.example.easylists.ui.theme.EasyListsTheme
import com.example.easylists.ui.theme.screens.MainScreen
import com.example.easylists.ui.theme.screens.ShopListScreen
import com.example.easylists.ui.theme.screens.SimpleListScreen
import com.example.easylists.ui.theme.screens.TodoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EasyListsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.MainScreen.route
                ) {
                    composable(route = Screens.MainScreen.route) {
                        MainScreen(navController)
                    }
                    composable(route = Screens.ShopList.route) {
                        ShopListScreen()
                    }
                    composable(route = Screens.ItemList.route) {
                        SimpleListScreen()
                    }
                    composable(route = Screens.TodoList.route) {
                        TodoListScreen()
                    }
                }
            }
        }
    }
}




