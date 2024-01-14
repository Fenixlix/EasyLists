package com.example.easylists.screen_item_list.ui.screens

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easylists.R
import com.example.easylists.screen_item_list.model.data_types.SimpleItem
import com.example.easylists.core_ui.interactive_comp.ItemInputBar
import com.example.easylists.screen_item_list.ui.components.SimListItem
import com.example.easylists.screen_item_list.viewmodel.ItemListViewModel

// An item quantity list oriented screen
//  With options to modify the amount and name of the items
@Composable
fun SimpleListScreen(itemListViewModel: ItemListViewModel = hiltViewModel()) {

    // ----- ViewModel related -----
    val itemList = itemListViewModel.itemList.collectAsState()

    // ----- Parameters related to the item list -----
    val (element, onElementChange) = remember { mutableStateOf("") }
    val (count, onCountChange) = remember { mutableStateOf(0) }

    // ----- Functions for the manipulation of the data in the list -----
    fun addComponent() {
        itemListViewModel.upsertItem(
            SimpleItem(
                name = element,
                count = count
            )
        )
        onCountChange(0)
        onElementChange("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
    )
    {
        //~~~~~ User input controls
        ItemInputBar(
            fieldText = element,
            fieldValue = count.toString(),
            fieldTextPlaceholder = stringResource(id = R.string.new_item_placeholder),
            onTextChange = {
                onElementChange(it)
            },
            onValueChange = {
                if (it.isNotEmpty()) onCountChange(it.toInt())
            },
            buttonEnabler = element.isNotEmpty() && count >= 0,
            buttonDrawable = painterResource(id = R.drawable.ic_add_circle_outline_24),
            onButtonClick = { addComponent() }
        )

        //~~~~~ List with the different components
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .border(2.dp, MaterialTheme.colors.secondaryVariant),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(itemList.value) {
                SimListItem(
                    listItemName = it.name,
                    listItemCount = it.count,
                    onUpButtonClick = {
                        itemListViewModel.upDownCount(
                            item = it,
                            isUpCount = true
                        )
                    },
                    onDownButtonClick = {
                        if (it.count != 0)
                            itemListViewModel.upDownCount(
                                item = it,
                                isUpCount = false
                            )
                        else itemListViewModel.deleteItem(it)
                    }
                )
            }
        }
    }
}