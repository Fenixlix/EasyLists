package com.example.easylists.model

sealed class Screens(val route: String, val title: String) {
    object MainScreen : Screens(route = "main_screen", title = "Main")
    object ShopList : Screens(route = "shop_list_screen", title = "Shopping")
    object ItemList : Screens(route = "item_list_screen", title = "Items")
    object TodoList : Screens(route = "todo_list_screen", title = "To do")
}
