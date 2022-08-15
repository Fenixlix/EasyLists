package com.example.easylists.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.R
import com.example.easylists.model.data_types.ShopItem
import com.example.easylists.ui.decorative_comp.PrettyVerticalWideSpacer
import com.example.easylists.ui.interactive_comp.ItemInputBar
import com.example.easylists.ui.interactive_comp.PromotionSelectBar
import com.example.easylists.ui.interactive_comp.ShoppingListItem
import java.lang.NumberFormatException

// A shop list oriented screen with a promotion select bar and auto summary
@Composable
fun ShopListScreen() {

    // Parameters related to the promotion bar
    val promotion = listOf(
        1f, 0.85f, 0.80f, 0.75f, 0.50f, 0.25f
    )
    var currentPromotion by remember { mutableStateOf(0) }

    // Parameters related to the shop list
    val (component, onComponentChange) = remember { mutableStateOf("") }
    val (componentValue, onValueChange) = remember { mutableStateOf(0.0f) }
    val componentsList = remember { mutableStateListOf<ShopItem>() }
    var totalValue by remember { mutableStateOf(0.0f) }

    // Functions for the manipulation of the data in the list
    fun addComponent() {
        componentsList.add(
            ShopItem(
                name = component,
                value = componentValue * promotion[currentPromotion],
                promotionCode = currentPromotion
            )
        )
    }

    fun computeTotalValue() {
        totalValue = 0.0f
        componentsList.forEach { totalValue += it.value }
    }

    fun deleteComponent(selectedItem: ShopItem) {
        componentsList.remove(selectedItem)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        PromotionSelectBar(
            promotions = promotion,
            currentPromotion = currentPromotion,
            onClick = { currentPromotion = it })

        //~~~~~ User input controls
        ItemInputBar(
            fieldText = component,
            fieldValue = componentValue.toString(),
            fieldTextPlaceholder = "Item Name",
            fieldValuePlaceholder = "0.0",
            onTextChange = {
                onValueChange(0.0f)
                onComponentChange(it)
            },
            onValueChange = {
                try {
                    onValueChange(it.toFloat())
                } catch (e: NumberFormatException) {

                }
            },
            buttonEnabler = component != "",
            buttonDrawable = painterResource(id = R.drawable.ic_add_circle_outline_24),
            onButtonClick = {
                addComponent()
                computeTotalValue()
            }
        )

        //~~~~~ List with the different components
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .border(2.dp, MaterialTheme.colors.secondaryVariant)
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(componentsList) {
                ShoppingListItem(
                    listItem = it,
                    buttonDrawable = painterResource(id = R.drawable.ic_delete_24)
                ) {
                    deleteComponent(it)
                    computeTotalValue()
                }
            }
        }

        PrettyVerticalWideSpacer()

        //~~~~~ Resume of the list value and total items
        ShoppingListItem(
            listItem = ShopItem(name = "Total", value = totalValue, -1),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            totItems = componentsList.size,
            buttonDrawable = null
        ) {}
    }
}

