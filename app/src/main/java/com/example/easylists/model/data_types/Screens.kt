package com.example.easylists.model.data_types

import com.example.easylists.R

enum class Screens(val route: String, val title: String, val icon: Int) {
    MainScreen(route = "main_screen", title = "Main", R.drawable.shop_plus_list_ico),
    ShopList(route = "shop_list_screen", title = "Shopping", R.drawable.shop_list_ico),
    ItemList(route = "item_list_screen", title = "Items", R.drawable.item_list_ico),
    ShopItemList(route = "shop_item_list_screen", title = "Shop+Items", R.drawable.shop_plus_list_ico),
    TodoList(route = "todo_list_screen", title = "To do", R.drawable.todo_list_ico)
}