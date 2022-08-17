package com.example.easylists.model.data_types

import androidx.compose.runtime.MutableState

data class ShopMultiItem(
    val name : String,
    val price : Float,
    val quantity : MutableState<Int>,
    val promotionCode : Int
)
