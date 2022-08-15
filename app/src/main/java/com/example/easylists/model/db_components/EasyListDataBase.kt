package com.example.easylists.model.db_components

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.easylists.model.data_types.TodoItem

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class EasyListDataBase : RoomDatabase() {

    abstract fun getTodoListDao() : DbTodoListDao

}