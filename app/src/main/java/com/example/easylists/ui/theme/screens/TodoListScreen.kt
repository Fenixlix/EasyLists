package com.example.easylists.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.easylists.R
import com.example.easylists.model.TodoItem
import com.example.easylists.ui.theme.interactive_comp.ItemInputBar
import com.example.easylists.ui.theme.interactive_comp.TodoListItem

@Composable
fun TodoListScreen() {

    val (todoTask, onTaskChange) = remember { mutableStateOf("") }
    val taskList = remember { mutableStateListOf<TodoItem>() }

    fun addTask(task: String) {
        taskList.add(TodoItem(task = task))
    }

    fun deleteTask(task: TodoItem) {
        taskList.remove(task)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemInputBar(
            fieldText = todoTask,
            onTextChange = onTaskChange,
            fieldTextPlaceholder = "New Task",
            fieldValue = null,
            onValueChange = null, buttonEnabler = todoTask.isNotEmpty(),
            buttonDrawable = painterResource(id = R.drawable.ic_add_circle_outline_24)
        ) {
            addTask(todoTask)
            onTaskChange("")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(taskList) { task ->
                TodoListItem(
                    task = task,
                    onClickDelete = { deleteTask(it) })
            }
        }
    }
}