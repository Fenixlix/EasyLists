package com.example.easylists.screen_shop_list.model.db_components

import androidx.room.*
import com.example.easylists.screen_shop_list.model.data_types.ShopItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DbShopListDao {

    @Upsert
    suspend fun upsertShopItem(shopItem: ShopItem)

    @Delete
    suspend fun deleteShopItem(shopItem: ShopItem)

    @Query("SELECT * FROM shop_item_list_table")
    fun getShopList() : Flow<List<ShopItem>>

}