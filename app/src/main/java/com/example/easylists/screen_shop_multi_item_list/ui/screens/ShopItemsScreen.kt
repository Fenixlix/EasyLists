package com.example.easylists.screen_shop_multi_item_list.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItem
import com.example.easylists.core_ui.decorative_comp.PrettyVerticalWideSpacer
import com.example.easylists.core_ui.interactive_comp.*
import com.example.easylists.screen_shop_list.ui.components.ShoppingListItem
import com.example.easylists.screen_shop_multi_item_list.ui.components.ShopMultiItemList
import com.example.easylists.screen_shop_multi_item_list.viewmodel.ShopMultiItemListViewModel

@Composable
fun ShopItemsScreen(shopMultiItemListViewModel: ShopMultiItemListViewModel = hiltViewModel()) {

    // ----- ViewModel related -----
    val shopMultiItemList = shopMultiItemListViewModel.shopMultiItemList.collectAsState()
    val resumeState = shopMultiItemListViewModel.resumeState.collectAsState()

    // ----- Parameters related to the promotion bar -----
    val promotionOptionsList = listOf(1f, 0.85f, 0.80f, 0.75f, 0.50f, 0.25f)
    var currentPromotion by remember { mutableStateOf(0) }

    // ----- Parameters related to the shop list -----
    val (shopItemName, onShopItemNameChange) = remember { mutableStateOf("") }
    val (shopItemPrice, onShopItemPriceChange) = remember { mutableStateOf(0.0f) }

    LaunchedEffect(key1 = true) {
        shopMultiItemListViewModel.updateResume()
    }

    // ----- Parameters for the control of the AlertDialog -----
    val showDialog = remember { mutableStateOf(false) }
    val itemToUpdate = remember {
        mutableStateOf(
            ShopMultiItem(
                name = "",
                price = 0.0f,
                quantity = 0,
                promotionCode = 0
            )
        )
    }

    // ----- Control functions for the different processes in the screen -----
    fun addComponent() {
        shopMultiItemListViewModel.upsertShopMultiItem(
            ShopMultiItem(
                name = shopItemName,
                price = shopItemPrice * promotionOptionsList[currentPromotion],
                quantity = 1,
                promotionCode = currentPromotion
            )
        )
        onShopItemNameChange("")
        onShopItemPriceChange(0.0f)
    }

    // ----- Alert Dialog -----
    ValueModDialog(
        showDialog = showDialog.value,
        itemValue = itemToUpdate.value.price,
        onDismiss = { showDialog.value = false },
        onOkClick = {
            shopMultiItemListViewModel.updatePrice(
                shopItem = itemToUpdate.value.copy(price = it),
                previousPrice = itemToUpdate.value.price
            )
            showDialog.value = false
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        PromotionSelectBar(
            promotions = promotionOptionsList,
            currentPromotion = currentPromotion,
            onClick = { currentPromotion = it })

        //~~~~~ User input controls
        ItemInputBar(
            fieldText = shopItemName,
            fieldValue = shopItemPrice.toString(),
            fieldTextPlaceholder = stringResource(id = R.string.item_name_placeholder),
            fieldValuePlaceholder = stringResource(id = R.string.value_0_0_placeholder),
            onTextChange = {
                onShopItemNameChange(it)
            },
            onValueChange = {
                onShopItemPriceChange(it.toFloat())
            },
            buttonEnabler = shopItemName.isNotEmpty(),
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
            items(shopMultiItemList.value) {
                ShopMultiItemList(
                    listItem = it,
                    onUpButtonClick = {
                        shopMultiItemListViewModel.upDownCount(
                            shopItem = it,
                            isUpCount = true
                        )
                    },
                    onDownButtonClick = {
                        if (it.quantity != 0)
                        shopMultiItemListViewModel.upDownCount(
                            shopItem = it,
                            isUpCount = false
                        )
                        else shopMultiItemListViewModel.deleteShopMultiItem(it)
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
                promotionCode = -1
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            totItems = resumeState.value.totItemsInList,
            buttonDrawable = null,
            onButtonClick = {},
            onValueClick = {}
        )
    }
}