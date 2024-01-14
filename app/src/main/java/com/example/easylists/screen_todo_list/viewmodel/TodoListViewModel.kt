package com.example.easylists.screen_todo_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_todo_list.model.data_types.TodoItem
import com.example.easylists.screen_todo_list.model.db_components.DbTodoListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoListDao: DbTodoListDao
) : ViewModel() {

    private val _todoList = todoListDao.getTodoList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = emptyList())

    val todoList = _todoList

    fun addTask(task: TodoItem) = viewModelScope.launch {
        todoListDao.addItem(task)
    }

    fun check(task: TodoItem) = viewModelScope.launch {
        todoListDao.checkItem(task)
    }

    fun deleteTask(task: TodoItem) = viewModelScope.launch {
        todoListDao.deleteItem(task)
    }

}