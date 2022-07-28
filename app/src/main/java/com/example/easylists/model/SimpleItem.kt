package com.example.easylists.model

import androidx.compose.runtime.MutableState

data class SimpleItem(
    val name: String,
    var count: MutableState<Int>)
