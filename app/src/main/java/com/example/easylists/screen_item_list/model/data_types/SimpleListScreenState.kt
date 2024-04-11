package com.example.easylists.screen_item_list.model.data_types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

@OptIn(ExperimentalFoundationApi::class)
data class SimpleListScreenState (
    val simpleItemList: List<SimpleItem> = emptyList(),
    val itemName : TextFieldState = TextFieldState(""),
    val itemQuantity : TextFieldState = TextFieldState("0")
)
