package com.example.easylists.screen_shop_list.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easylists.R
import com.example.easylists.screen_shop_list.model.data_types.ShopItem
import com.example.easylists.core_ui.decorative_comp.PrettyVerticalWideSpacer
import com.example.easylists.core_ui.interactive_comp.ItemInputBar
import com.example.easylists.core_ui.interactive_comp.PromotionSelectBar
import com.example.easylists.screen_shop_list.ui.components.ShoppingListItem
import com.example.easylists.core_ui.interactive_comp.ValueModDialog
import com.example.easylists.screen_shop_list.viewmodel.ShopListViewModel

// A shop list oriented screen with a promotion select bar and auto summary
@Composable
fun ShopListScreen(shopListViewModel: ShopListViewModel = hiltViewModel()) {

    // ----- ViewModels related -----
    val shopItemList = shopListViewModel.shopList.collectAsState()
    val resumeState = shopListViewModel.resumeState.collectAsState()

    // ----- Parameters related to the promotion bar -----
    val promotion = listOf(1f, 0.85f, 0.80f, 0.75f, 0.50f, 0.25f)
    var currentPromotion by remember { mutableStateOf(0) }

    // ----- Parameters related to the shop list -----
    val (component, onComponentChange) = remember { mutableStateOf("") }
    val (componentValue, onValueChange) = remember { mutableStateOf(0.0f) }

    // ----- Parameters for the control of the AlertDialog -----
    val showDialog = remember { mutableStateOf(false) }
    val itemToUpdate = remember { mutableStateOf(
        ShopItem("",0.0f, 0)
    ) }

    LaunchedEffect(key1 = true){
        shopListViewModel.updateResume()
    }

    fun addComponent() {
        shopListViewModel.upsertShopItem(
            ShopItem(
            name = component,
            price = componentValue * promotion[currentPromotion],
            promotionCode = currentPromotion)
        )
        onComponentChange("")
        onValueChange(0.0f)
    }

    fun deleteComponent(selectedItem: ShopItem) {
        shopListViewModel.deleteShopItem(shopItem = selectedItem)
    }

    // ----- Alert Dialog -----
    ValueModDialog(
        showDialog = showDialog.value,
        itemValue = itemToUpdate.value.price,
        onDismiss = { showDialog.value = false },
        onOkClick = {
            shopListViewModel.updateShopItemPrice(
                shopItem = itemToUpdate.value.copy(price = it),
                previousPrice = itemToUpdate.value.price)
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
            fieldTextPlaceholder = stringResource(id = R.string.item_name_placeholder),
            fieldValuePlaceholder = stringResource(id = R.string.value_0_0_placeholder),
            onTextChange = {
                onComponentChange(it)
            },
            onValueChange = {
                onValueChange(it.toFloat())
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
            items(shopItemList.value) {
                ShoppingListItem(
                    listItem = it,
                    buttonDrawable = painterResource(id = R.drawable.ic_delete_24),
                    onButtonClick = {
                        deleteComponent(it)
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
            listItem = ShopItem(
                name = stringResource(id = R.string.total_price),
                price = resumeState.value.totPriceAmount,
                promotionCode = -1),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            totItems = resumeState.value.totItemsInList,
            buttonDrawable = null,
            onButtonClick = {},
            onValueClick = {}
        )
    }
}

