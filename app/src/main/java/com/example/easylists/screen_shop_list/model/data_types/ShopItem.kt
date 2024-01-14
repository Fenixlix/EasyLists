package com.example.easylists.screen_shop_list.model.data_types

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_item_list_table")
data class ShopItem(
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "price")val price: Float,
    @ColumnInfo(name = "promotion_code")val promotionCode: Int,
    @PrimaryKey(autoGenerate = true) val id : Int = 0
)