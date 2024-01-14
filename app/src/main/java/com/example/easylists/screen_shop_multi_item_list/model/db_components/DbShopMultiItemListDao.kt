package com.example.easylists.screen_shop_multi_item_list.model.db_components

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DbShopMultiItemListDao {

    @Upsert
    suspend fun upsertShopMultiItem(item: ShopMultiItem)

    @Delete
    suspend fun deleteShopMultiItem(item: ShopMultiItem)

    @Query("SELECT * FROM shop_multi_item_list_table")
    fun getShopMultiItemList() : Flow<List<ShopMultiItem>>

}