package com.example.easylists.screen_shop_multi_item_list.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easylists.R
import com.example.easylists.core_ui.decorative_comp.PrettyVerticalWideSpacer
import com.example.easylists.core_ui.interactive_comp.ItemInputBar
import com.example.easylists.core_ui.interactive_comp.PromotionSelectBar
import com.example.easylists.core_ui.interactive_comp.ValueModDialog
import com.example.easylists.screen_shop_list.model.data_types.ShopItem
import com.example.easylists.screen_shop_list.ui.components.ShoppingListItem
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItem
import com.example.easylists.screen_shop_multi_item_list.ui.components.ShopMultiItemList
import com.example.easylists.screen_shop_multi_item_list.viewmodel.ShopMultiItemListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShopItemsScreen(shopMultiItemListViewModel: ShopMultiItemListViewModel = hiltViewModel()) {

    val state = shopMultiItemListViewModel.state.collectAsState()
    val btnEnabler by remember {
        derivedStateOf {
            state.value.itemName.text.isNotEmpty() && state.value.itemPrice.text.isNotEmpty()
        }
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    // ----- Parameters for the control of the AlertDialog -----
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

    // ----- Alert Dialog -----
    ValueModDialog(
        showDialog = state.value.showDialog,
        itemValue = itemToUpdate.value.price,
        onDismiss = { shopMultiItemListViewModel.toggleDialog() },
        onOkClick = {
            shopMultiItemListViewModel.updatePrice(
                shopItem = itemToUpdate.value.copy(price = it),
                previousPrice = itemToUpdate.value.price
            )
            shopMultiItemListViewModel.toggleDialog()
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        PromotionSelectBar(
            promotions = shopMultiItemListViewModel.promotion,
            currentPromotion = state.value.discountPointer,
            onClick = { shopMultiItemListViewModel.changePromotion(it) })

        //~~~~~ User input controls
        ItemInputBar(
            textField = state.value.itemName,
            numberField = state.value.itemPrice,
            buttonEnabler = btnEnabler,
            textPlaceholder = stringResource(id = R.string.item_name_placeholder),
            keyboardController = keyboardController,
            onButtonClick = { shopMultiItemListViewModel.upsertShopMultiItem() }
        )

        //~~~~~ List with the different components
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .border(2.dp, MaterialTheme.colorScheme.secondary)
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(state.value.itemList) {
                ShopMultiItemList(
                    listItem = it,
                    onUpButtonClick = {
                        shopMultiItemListViewModel.upDownCount(
                            shopItem = it,
                            isUpCount = true
                        )
                    },
                    onDownButtonClick = {
                        shopMultiItemListViewModel.upDownCount(
                            shopItem = it,
                            isUpCount = false
                        )
                    },
                    onValueClick = { modValueItem ->
                        itemToUpdate.value = modValueItem
                        shopMultiItemListViewModel.toggleDialog()
                    }
                )
            }
        }

        PrettyVerticalWideSpacer()

        //~~~~~ Resume of the list value and total items
        ShoppingListItem(
            listItem = ShopItem(
                name = stringResource(id = R.string.total_price),
                price = state.value.totalPrice,
                promotionCode = -1
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            totItems = state.value.totalItems,
            buttonIcon = null,
            onButtonClick = {},
            onValueClick = {}
        )
    }
}