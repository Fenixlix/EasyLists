package com.example.easylists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easylists.core_model.data_types.Screens
import com.example.easylists.core_ui.screens.MainScreen
import com.example.easylists.core_ui.screens.SplashScreen
import com.example.easylists.core_ui.theme.EasyListsTheme
import com.example.easylists.screen_item_list.ui.screens.SimpleListScreen
import com.example.easylists.screen_shop_list.ui.screens.ShopListScreen
import com.example.easylists.screen_shop_multi_item_list.ui.screens.ShopItemsScreen
import com.example.easylists.screen_todo_list.ui.screens.TodoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EasyListsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.SplashScreen.route
                ) {
                    composable(route = Screens.SplashScreen.route) {
                        SplashScreen {
                            navController.popBackStack()
                            navController.navigate(Screens.MainScreen.route)
                        }
                    }
                    composable(route = Screens.MainScreen.route) {
                        MainScreen { navController.navigate(it) }
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
                    composable(route = Screens.ShopItemList.route) {
                        ShopItemsScreen()
                    }
                }
            }
        }
    }
}