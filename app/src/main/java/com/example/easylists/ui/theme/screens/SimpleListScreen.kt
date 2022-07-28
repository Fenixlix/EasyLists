package com.example.easylists.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.easylists.R
import com.example.easylists.model.SimpleItem
import com.example.easylists.ui.theme.interactive_comp.ItemInputBar
import com.example.easylists.ui.theme.interactive_comp.SimListItem

// An item count list oriented screen with multiple items counts and title
@Composable
fun SimpleListScreen() {

    // Parameters related to the item list
    val (element, onElementChange) = remember { mutableStateOf("") }
    val (count, onCountChange) = remember { mutableStateOf(0) }
    val itemsList = remember { mutableStateListOf<SimpleItem>() }

    // Functions for the manipulation of the data in the list
    fun addComponent() {
        if (element.isNotEmpty() && count >= 0)
            itemsList.add(SimpleItem(name = element, count = mutableStateOf(count)))
        onElementChange("")
    }

    fun deleteComponent(selectedItem: SimpleItem) {
        itemsList.remove(selectedItem)
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
            fieldTextPlaceholder = "New Item",
            onTextChange = {
                onCountChange(1)
                onElementChange(it)
            },
            onValueChange = {
                if (it.isNotEmpty()) onCountChange(it.toInt())
            },
            buttonEnabler = element.isNotEmpty(),
            buttonDrawable = painterResource(id = R.drawable.ic_add_circle_outline_24),
            onButtonClick = {
                addComponent()
            }
        )

        //~~~~~ List with the different components
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .border(2.dp, MaterialTheme.colors.secondaryVariant),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(itemsList) {
                SimListItem(
                    listItemName = it.name,
                    listItemCount = it.count.value,
                    onUpButtonClick = { it.count.value += 1 },
                    onDownButtonClick = {
                        if (it.count.value == 1) deleteComponent(it)
                        else it.count.value -= 1
                    }
                )
            }
        }
    }
}