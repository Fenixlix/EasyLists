package com.example.easylists.screen_item_list.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_item_list.model.data_types.SimpleItem
import com.example.easylists.screen_item_list.model.data_types.SimpleListScreenState
import com.example.easylists.screen_item_list.model.db_components.DbItemListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemListDao: DbItemListDao
) : ViewModel() {

    private val _screenState = MutableStateFlow(SimpleListScreenState())
    private val _simpleItemList = MutableStateFlow(emptyList<SimpleItem>())

    val screenState = combine(_screenState, _simpleItemList) { state, itemList ->
        state.itemName.clearText()
        state.itemQuantity.edit {
            replace(0, length, "0")
        }
        state.copy(
            simpleItemList = itemList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SimpleListScreenState()
    )

    init {
        viewModelScope.launch {
            itemListDao.getItemList().collect { itemList ->
                _simpleItemList.update {
                    itemList
                }
            }
        }
    }

    fun upDownCount(item: SimpleItem, isUpCount: Boolean) = viewModelScope.launch {
        if (isUpCount) {
            itemListDao.upsertItem(item.copy(quantity = item.quantity + 1))
        } else if (item.quantity == 1) {
            deleteItem(item)
        } else {
            itemListDao.upsertItem(item.copy(quantity = item.quantity - 1))
        }
    }

    fun upsertItem() = viewModelScope.launch {
        val newItem = SimpleItem(
            name = _screenState.value.itemName.text.toString(),
            quantity = _screenState.value.itemQuantity.text.toString().toInt()
        )
        itemListDao.upsertItem(newItem)
    }

    private fun deleteItem(item: SimpleItem) = viewModelScope.launch {
        itemListDao.deleteItem(item)
    }

}