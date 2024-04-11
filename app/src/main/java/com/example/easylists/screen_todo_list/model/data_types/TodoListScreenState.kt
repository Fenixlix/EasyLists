package com.example.easylists.screen_todo_list.model.data_types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

@OptIn(ExperimentalFoundationApi::class)
data class TodoListScreenState(
    val taskList : List<TodoItem> = emptyList(),
    val task : TextFieldState = TextFieldState(""),
)
