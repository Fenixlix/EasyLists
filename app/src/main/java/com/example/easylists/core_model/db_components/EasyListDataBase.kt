package com.example.easylists.core_model.db_components

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.easylists.screen_item_list.model.data_types.SimpleItem
import com.example.easylists.screen_item_list.model.db_components.DbItemListDao
import com.example.easylists.screen_shop_list.model.data_types.ShopItem
import com.example.easylists.screen_shop_list.model.db_components.DbShopListDao
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItem
import com.example.easylists.screen_shop_multi_item_list.model.db_components.DbShopMultiItemListDao
import com.example.easylists.screen_todo_list.model.data_types.TodoItem
import com.example.easylists.screen_todo_list.model.db_components.DbTodoListDao

@Database(
    entities = [
        ShopItem::class,
        SimpleItem::class,
        ShopMultiItem::class,
        TodoItem::class],
    version = 1,
    exportSchema = false)
abstract class EasyListDataBase : RoomDatabase() {

    abstract fun getShopListDao() : DbShopListDao

    abstract fun getItemListDao() : DbItemListDao

    abstract fun getShopMultiItemListDao() : DbShopMultiItemListDao

    abstract fun getTodoListDao() : DbTodoListDao

}