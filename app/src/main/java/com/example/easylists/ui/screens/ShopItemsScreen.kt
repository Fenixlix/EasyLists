package com.example.easylists.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.R
import com.example.easylists.model.data_types.ShopItem
import com.example.easylists.model.data_types.ShopMultiItem
import com.example.easylists.ui.decorative_comp.PrettyVerticalWideSpacer
import com.example.easylists.ui.interactive_comp.*
import java.lang.NumberFormatException

@Composable
fun ShopItemsScreen(){

    // ----- Parameters related to the promotion bar -----
    val promotion = listOf(1f, 0.85f, 0.80f, 0.75f, 0.50f, 0.25f)
    var currentPromotion by remember { mutableStateOf(0) }

    // ----- Parameters related to the shop list -----
    val (component, onComponentChange) = remember { mutableStateOf("") }
    val (componentValue, onValueChange) = remember { mutableStateOf(0.0f) }
    val (totalValue, refreshTotalValue) = remember { mutableStateOf(0.0f) }
    val (totalQuantity, refreshTotalQuantity) = remember { mutableStateOf(0) }

    val shopMultiItemList = remember { mutableStateListOf<ShopMultiItem>() }

    // ----- Parameters for the control of the AlertDialog -----
    val showDialog = remember { mutableStateOf(false) }
    val itemToUpdate = remember { mutableStateOf( ShopMultiItem(
        "",0.0f, mutableStateOf(0), 0)) }


    // ----- Control functions for the different processes in the screen -----
    fun addComponent() {
        shopMultiItemList.add(
            ShopMultiItem(
                name = component,
                price = componentValue * promotion[currentPromotion],
                quantity = mutableStateOf(1),
                promotionCode = currentPromotion
            )
        )
        refreshTotalQuantity(totalQuantity+1)
        refreshTotalValue(totalValue+componentValue)
        onComponentChange("")
        onValueChange(0.0f)
    }

    fun upCount(selectedItem : ShopMultiItem){
        selectedItem.quantity.value += 1
        refreshTotalQuantity(totalQuantity+1)
        refreshTotalValue(totalValue + selectedItem.price)
    }

    fun downCount(selectedItem: ShopMultiItem) {
        if (selectedItem.quantity.value == 0) {
            shopMultiItemList.remove(selectedItem)
        } else {
            selectedItem.quantity.value -= 1
            refreshTotalQuantity(totalQuantity-1)
            refreshTotalValue(totalValue - selectedItem.price)
        }
    }

    fun updateTotValue(){
        var newTotValueVessel = 0.0f
        shopMultiItemList.forEach {
            newTotValueVessel += it.price*it.quantity.value
        }
        refreshTotalValue(newTotValueVessel)
    }

    // ----- Alert Dialog -----
    ValueModDialog(
        showDialog = showDialog.value,
        itemValue = itemToUpdate.value.price,
        onDismiss = {showDialog.value = false},
        onOkClick = {
            shopMultiItemList[shopMultiItemList.indexOf(itemToUpdate.value)] =
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
                    Log.e("ShopItem", "ShopItemsScreen: ${e.message}")
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
            items(shopMultiItemList) {
                ShopMultiItemList(
                    listItem = it,
                    onUpButtonClick = {upCount(it)},
                    onDownButtonClick = {downCount(it)},
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
            totItems = totalQuantity,
            buttonDrawable = null,
            onButtonClick = {},
            onValueClick = {}
        )
    }

}