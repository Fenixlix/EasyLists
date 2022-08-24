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
import com.example.easylists.ui.interactive_comp.ValueModDialog
import java.lang.NumberFormatException

// A shop list oriented screen with a promotion select bar and auto summary
@Composable
fun ShopListScreen() {

    // ----- Parameters related to the promotion bar -----
    val promotion = listOf(
        1f, 0.85f, 0.80f, 0.75f, 0.50f, 0.25f
    )
    var currentPromotion by remember { mutableStateOf(0) }

    // ----- Parameters related to the shop list -----
    val (component, onComponentChange) = remember { mutableStateOf("") }
    val (componentValue, onValueChange) = remember { mutableStateOf(0.0f) }
    val componentsList = remember { mutableStateListOf<ShopItem>() }
    val (totalValue, refreshTotalValue) = remember { mutableStateOf(0.0f) }

    // ----- Parameters for the control of the AlertDialog -----
    val showDialog = remember { mutableStateOf(false) }
    val itemToUpdate = remember { mutableStateOf(ShopItem(
        "",0.0f, 0)) }

    // ----- Functions for the manipulation of the data in the list -----
    fun refreshTotal() {
        //totalValue = 0.0f
        //componentsList.forEach { totalValue += it.value }
        var vesselPriceVariable = 0.0
        componentsList.forEach {
            vesselPriceVariable += it.price
        }
        refreshTotalValue(vesselPriceVariable.toFloat())
    }

    fun addComponent() {
        componentsList.add(
            ShopItem(
                name = component,
                price = componentValue * promotion[currentPromotion],
                promotionCode = currentPromotion
            )
        )
        refreshTotal()
        onComponentChange("")
        onValueChange(0.0f)
    }

    fun deleteComponent(selectedItem: ShopItem) {
        componentsList.remove(selectedItem)
    }

    fun updateTotValue() {
        var newTotValueVessel = 0.0f
        componentsList.forEach {
            newTotValueVessel += it.price
        }
        refreshTotalValue(newTotValueVessel)
    }

    // ----- Alert Dialog -----
    ValueModDialog(
        showDialog = showDialog.value,
        itemValue = itemToUpdate.value.price,
        onDismiss = { showDialog.value = false },
        onOkClick = {
            componentsList[componentsList.indexOf(itemToUpdate.value)] =
                itemToUpdate.value.copy(price = it)
            updateTotValue()
            showDialog.value = false
        }
    )

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
                onComponentChange(it)
            },
            onValueChange = {
                try {
                    onValueChange(it.toFloat())
                } catch (e: NumberFormatException) {

                }
            },
            buttonEnabler = component.isNotEmpty(),
            buttonDrawable = painterResource(id = R.drawable.ic_add_circle_outline_24),
            onButtonClick = { addComponent() }
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
                    buttonDrawable = painterResource(id = R.drawable.ic_delete_24),
                    onButtonClick = {
                        deleteComponent(it)
                        refreshTotal()
                    },
                    onValueClick = { modValueItem ->
                        itemToUpdate.value = modValueItem
                        showDialog.value = true
                    }
                )
            }
        }

        PrettyVerticalWideSpacer()

        //~~~~~ Resume of the list value and total items
        ShoppingListItem(
            listItem = ShopItem(name = "Total", price = totalValue, -1),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            totItems = componentsList.size,
            buttonDrawable = null,
            onButtonClick = {},
            onValueClick = {}
        )
    }
}

