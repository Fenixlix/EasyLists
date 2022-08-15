package com.example.easylists.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easylists.R
import com.example.easylists.model.data_types.TodoItem
import com.example.easylists.ui.interactive_comp.ItemInputBar
import com.example.easylists.ui.interactive_comp.TodoListItem
import com.example.easylists.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(todoViewModel: TodoViewModel = hiltViewModel()) {

    val (todoTask, onTaskChange) = remember { mutableStateOf("") }

    val taskList = todoViewModel.todoItemList

    fun addTask(task: String) {
        todoViewModel.addTask(TodoItem(task = task))
    }

    fun checkTask(task : TodoItem){
        todoViewModel.check(task.copy(isChecked = task.isChecked.not()))
    }

    fun deleteTask(task: TodoItem) {
        todoViewModel.deleteTask(task)
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
                    onCheck = {checkTask(it)},
                    onClickDelete = { deleteTask(it) })
            }
        }
    }
}