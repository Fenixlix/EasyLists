package com.example.easylists.screen_item_list.ui.screens

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easylists.R
import com.example.easylists.core_ui.interactive_comp.ItemInputBar
import com.example.easylists.screen_item_list.ui.components.SimListItem
import com.example.easylists.screen_item_list.viewmodel.ItemListViewModel

// An item quantity list oriented screen
//  With options to modify the amount and name of the items
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleListScreen(
    simpleListViewModel: ItemListViewModel = hiltViewModel()
) {

    val state = simpleListViewModel.screenState.collectAsState()
    val buttonEnabler = remember {
        derivedStateOf {
            state.value.itemName.text.isNotEmpty()
        }
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    )
    {
        //~~~~~ User input controls
        ItemInputBar(
            textField = state.value.itemName,
            numberField = state.value.itemQuantity,
            textPlaceholder = stringResource(id = R.string.item_name_placeholder),
            buttonEnabler = buttonEnabler.value,
            keyboardController = keyboardController,
            onButtonClick = { simpleListViewModel.upsertItem() }
        )

        //~~~~~ List with the different components
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .border(2.dp, MaterialTheme.colorScheme.secondary),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(state.value.simpleItemList) {
                SimListItem(
                    listItemName = it.name,
                    listItemCount = it.quantity,
                    onUpButtonClick = { simpleListViewModel.upDownCount(it, true) },
                    onDownButtonClick = { simpleListViewModel.upDownCount(it, false) }
                )
            }
        }
    }
}