package com.example.easylists.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TodoItem(
    val task : String,
    val isChecked : MutableState<Boolean> = mutableStateOf(false)
)
