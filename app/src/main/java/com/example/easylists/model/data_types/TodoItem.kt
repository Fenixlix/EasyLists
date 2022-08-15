package com.example.easylists.model.data_types

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "task") val task : String,
    @ColumnInfo(name = "is_checked") val isChecked : Boolean = false
)

// @ColumnInfo(name = "is_checked") val isChecked : MutableState<Boolean> = mutableStateOf(false)