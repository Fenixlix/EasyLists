package com.example.easylists.screen_item_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_item_list.model.data_types.SimpleItem
import com.example.easylists.screen_item_list.model.db_components.DbItemListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemListDao: DbItemListDao
) : ViewModel() {

    private val _itemList = MutableStateFlow<List<SimpleItem>>(emptyList())

    val itemList = _itemList.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            itemListDao.getItemList().collect { itemList ->
                _itemList.value = itemList
            }
        }
    }

    fun upDownCount(item: SimpleItem, isUpCount : Boolean) = viewModelScope.launch {
        itemListDao.upsertItem(item.copy(count = item.count + if(isUpCount) 1 else -1))
    }


    fun upsertItem(item: SimpleItem) = viewModelScope.launch {
        itemListDao.upsertItem(item)
    }

    fun deleteItem(item: SimpleItem) = viewModelScope.launch {
        itemListDao.deleteItem(item)
    }

}