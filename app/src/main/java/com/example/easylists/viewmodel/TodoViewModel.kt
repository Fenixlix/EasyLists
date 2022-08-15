package com.example.easylists.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.model.data_types.TodoItem
import com.example.easylists.model.db_components.DbTodoListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoListDao: DbTodoListDao
) : ViewModel() {

    private val _todoItemList = mutableStateListOf<TodoItem>()
    val todoItemList : List<TodoItem> = _todoItemList

    init {
        viewModelScope.launch {
            todoListDao.getTodoList().collect{
                _todoItemList.clear()
                _todoItemList.addAll(it.toMutableStateList())
            }
        }
    }

    fun addTask(task: TodoItem) = viewModelScope.launch {
        todoListDao.addItem(task)
    }

    fun check(task: TodoItem) = viewModelScope.launch {
        todoListDao.checkItem(task)
    }

    fun deleteTask(task: TodoItem) = viewModelScope.launch {
        todoListDao.deleteItem(task)
    }

    fun deleteAllCheckedTasks(taskList : List<TodoItem>) = viewModelScope.launch{
        todoListDao.deleteAllCheckedItems(taskList)
    }

}