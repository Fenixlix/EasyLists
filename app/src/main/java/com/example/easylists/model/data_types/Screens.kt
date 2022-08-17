package com.example.easylists.model.data_types

enum class Screens(val route: String, val title: String) {
    MainScreen(route = "main_screen", title = "Main"),
    ShopList(route = "shop_list_screen", title = "Shopping"),
    ItemList(route = "item_list_screen", title = "Items"),
    TodoList(route = "todo_list_screen", title = "To do"),
    ShopItemList(route = "shop_item_list_screen", title = "Shop+Items")
}
// todo: add the individual logos for the main screen