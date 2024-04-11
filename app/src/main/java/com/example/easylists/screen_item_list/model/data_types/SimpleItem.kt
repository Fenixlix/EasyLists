package com.example.easylists.screen_item_list.model.data_types

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_list_table")
data class SimpleItem(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @PrimaryKey(autoGenerate = true) val id : Int = 0
)
