package com.example.easylists.screen_item_list.model.db_components

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.easylists.screen_item_list.model.data_types.SimpleItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DbItemListDao {

    @Upsert
    suspend fun upsertItem(item: SimpleItem)

    @Delete
    suspend fun deleteItem(item: SimpleItem)

    @Query("SELECT * FROM item_list_table")
    fun getItemList() : Flow<List<SimpleItem>>
}