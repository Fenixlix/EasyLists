package com.example.easylists.screen_shop_multi_item_list.model.data_types

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_multi_item_list_table")
data class ShopMultiItem(
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "price") val price : Float,
    @ColumnInfo(name = "quantity") val quantity : Int,
    @ColumnInfo(name = "promotion_code") val promotionCode : Int,
    @PrimaryKey(autoGenerate = true) val id : Int = 0
)
