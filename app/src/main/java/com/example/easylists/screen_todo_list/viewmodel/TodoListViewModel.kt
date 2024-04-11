package com.example.easylists.screen_todo_list.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_todo_list.model.data_types.TodoItem
import com.example.easylists.screen_todo_list.model.data_types.TodoListScreenState
import com.example.easylists.screen_todo_list.model.db_components.DbTodoListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoListDao: DbTodoListDao
) : ViewModel() {

    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    private val _screenState = MutableStateFlow(TodoListScreenState())

    val state = combine(_screenState, _todoList) { state, taskList ->
        state.task.clearText()
        state.copy(
            taskList = taskList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TodoListScreenState()
    )

    init {
        viewModelScope.launch {
            todoListDao.getTodoList().collect { newList ->
                _todoList.update {
                    newList
                }
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

}