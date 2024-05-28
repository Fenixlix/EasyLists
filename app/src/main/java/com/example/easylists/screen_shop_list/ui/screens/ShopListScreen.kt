package com.example.easylists.screen_shop_list.ui.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.example.easylists.screen_shop_list.viewmodel.ShopListViewModel

// A shop list oriented screen with a promotion select bar and auto summary
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShopListScreen(shopListViewModel: ShopListViewModel = hiltViewModel()) {

    // ----- ViewModels related -----
    val state = shopListViewModel.state.collectAsState()
    val btnEnabler by remember {
        derivedStateOf {
            state.value.itemName.text.isNotEmpty() && state.value.itemPrice.text.isNotEmpty()
        }
    }

    // ----- Parameters for the control of the AlertDialog -----
    val itemToUpdate = remember {
        mutableStateOf(
            ShopItem("", 0.0f, 0)
        )
    }

    // ----- Alert Dialog -----
    ValueModDialog(
        showDialog = state.value.showDialog,
        itemValue = itemToUpdate.value.price,
        onDismiss = { shopListViewModel.toggleDialog() },
        onOkClick = {
            shopListViewModel.updateShopItemPrice(
                shopItem = itemToUpdate.value.copy(price = it),
                previousPrice = itemToUpdate.value.price
            )
            shopListViewModel.toggleDialog()
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        PromotionSelectBar(
            promotions = shopListViewModel.promotion,
            currentPromotion = state.value.discountPointer,
            onClick = { shopListViewModel.changePromotion(it) })

        //~~~~~ User input controls
        ItemInputBar(
            textField = state.value.itemName,
            numberField = state.value.itemPrice,
            textPlaceholder = stringResource(id = R.string.item_name_placeholder),
            buttonEnabler = btnEnabler,
            onButtonClick = { shopListViewModel.upsertShopItem() }
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
                ShoppingListItem(
                    listItem = it,
                    buttonIcon = Icons.Filled.Delete,
                    onButtonClick = {
                        shopListViewModel.deleteShopItem(shopItem = it)
                    },
                    onValueClick = { modValueItem ->
                        itemToUpdate.value = modValueItem
                        shopListViewModel.toggleDialog()
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

