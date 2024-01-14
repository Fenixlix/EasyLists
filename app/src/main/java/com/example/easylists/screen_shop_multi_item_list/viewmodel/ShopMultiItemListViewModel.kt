package com.example.easylists.screen_shop_multi_item_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItem
import com.example.easylists.screen_shop_multi_item_list.model.db_components.DbShopMultiItemListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopMultiItemListViewModel @Inject constructor(
    private val shopMultiItemListDao: DbShopMultiItemListDao
) : ViewModel() {

    private val _shopMultiItemList = MutableStateFlow<List<ShopMultiItem>>(emptyList())
    private val _resumeState = MutableStateFlow(ShopItemsResumeState())

    val shopMultiItemList = _shopMultiItemList.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = emptyList()
    )

    val resumeState = _resumeState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = ShopItemsResumeState()
    )

    init {
        viewModelScope.launch {
            shopMultiItemListDao.getShopMultiItemList().collect {
                _shopMultiItemList.value = it
            }
        }
    }

    fun updateResume() {
        var initialTotPrice = 0.0f
        _shopMultiItemList.value.forEach {
            initialTotPrice += it.price * it.quantity
        }
        _resumeState.update {
            it.copy(
                totItemsInList = _shopMultiItemList.value.size,
                totPriceAmount = initialTotPrice
            )
        }
    }

    fun updatePrice(shopItem: ShopMultiItem, previousPrice: Float) = viewModelScope.launch {
        _resumeState.update {
            it.copy(
                totPriceAmount = it.totPriceAmount
                        - previousPrice*shopItem.quantity
                        + shopItem.price*shopItem.quantity
            )
        }
        shopMultiItemListDao.upsertShopMultiItem(item = shopItem)
    }

    fun upDownCount(shopItem: ShopMultiItem, isUpCount: Boolean) = viewModelScope.launch {
        _resumeState.update {
            it.copy(
                totPriceAmount = it.totPriceAmount +
                        if (isUpCount) shopItem.price else -shopItem.price
            )
        }
        shopMultiItemListDao.upsertShopMultiItem(shopItem.copy(
            quantity = shopItem.quantity + if (isUpCount) 1 else -1
        ))
    }

    fun upsertShopMultiItem(item: ShopMultiItem) = viewModelScope.launch {
        shopMultiItemListDao.upsertShopMultiItem(item)
        _resumeState.update {
            it.copy(
                totPriceAmount = it.totPriceAmount + item.price,
                totItemsInList = it.totItemsInList + 1
            )
        }
    }

    fun deleteShopMultiItem(item: ShopMultiItem) = viewModelScope.launch {
        shopMultiItemListDao.deleteShopMultiItem(item)
        _resumeState.update {
            it.copy(
                totItemsInList = it.totItemsInList - 1
            )
        }
    }
}

data class ShopItemsResumeState(
    val totItemsInList: Int = 0,
    val totPriceAmount: Float = 0.0f
)
