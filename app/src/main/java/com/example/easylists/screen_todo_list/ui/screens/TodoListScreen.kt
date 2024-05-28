package com.example.easylists.screen_todo_list.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easylists.R
import com.example.easylists.core_ui.interactive_comp.ItemInputBar
import com.example.easylists.screen_todo_list.model.data_types.TodoItem
import com.example.easylists.screen_todo_list.ui.components.TodoListItem
import com.example.easylists.screen_todo_list.viewmodel.TodoListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoListScreen(todoListViewModel: TodoListViewModel = hiltViewModel()) {

    val state = todoListViewModel.state.collectAsState()
    val btnEnabler by remember {
        derivedStateOf { state.value.task.text.isNotEmpty() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemInputBar(
            textField = state.value.task,
            textPlaceholder = stringResource(id = R.string.new_task),
            buttonEnabler = btnEnabler
        ) {
            todoListViewModel.addTask(TodoItem(task = state.value.task.text.toString()))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.value.taskList) { task ->
                TodoListItem(
                    task = task,
                    onCheck = { todoListViewModel.check(task.copy(isChecked = task.isChecked.not())) },
                    onClickDelete = { todoListViewModel.deleteTask(task) })
            }
        }
    }
}