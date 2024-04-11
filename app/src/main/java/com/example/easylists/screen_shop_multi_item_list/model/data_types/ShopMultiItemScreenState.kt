package com.example.easylists.screen_shop_multi_item_list.model.data_types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

@OptIn(ExperimentalFoundationApi::class)
data class ShopMultiItemScreenState(
    val itemList: List<ShopMultiItem> = emptyList(),
    val itemName: TextFieldState = TextFieldState(""),
    val itemPrice: TextFieldState = TextFieldState("0.0"),
    val discountPointer: Int = 0,
    val totalItems: Int = 0,
    val totalPrice: Float = 0.0f,
    val showDialog: Boolean = false
)