package com.example.easylists.screen_todo_list.model.db_components

import androidx.room.*
import com.example.easylists.screen_todo_list.model.data_types.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DbTodoListDao {

    @Query("SELECT * FROM todo_list_table")
    fun getTodoList() : Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: TodoItem)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun checkItem(item: TodoItem)

    @Delete
    suspend fun deleteItem(item: TodoItem)

}