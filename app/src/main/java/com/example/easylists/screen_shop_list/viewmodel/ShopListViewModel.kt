package com.example.easylists.screen_shop_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_shop_list.model.data_types.ShopItem
import com.example.easylists.screen_shop_list.model.db_components.DbShopListDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListViewModel @Inject constructor(
    private val shopListDao: DbShopListDao
) : ViewModel() {

    private val _shopList = MutableStateFlow<List<ShopItem>>(emptyList())
    private val _resumeState = MutableStateFlow(ShopItemResumeState())

    val shopList = _shopList.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = emptyList()
    )

    val resumeState = _resumeState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = ShopItemResumeState()
    )

    init {
        viewModelScope.launch {
            shopListDao.getShopList().collect { shopItemList ->
                _shopList.value = shopItemList
            }
        }
    }

    fun updateResume() {
        var initialTotPrice = 0.0f
        _shopList.value.forEach {
            initialTotPrice += it.price
        }
        _resumeState.update {
            it.copy(
                totItemsInList = _shopList.value.size,
                totPriceAmount = initialTotPrice
            )
        }
    }

    fun updateShopItemPrice(shopItem: ShopItem, previousPrice: Float) = viewModelScope.launch {
        _resumeState.update {
            it.copy(
                totPriceAmount = it.totPriceAmount
                        -previousPrice + shopItem.price
            )
        }
        shopListDao.upsertShopItem(shopItem = shopItem)
    }

    fun upsertShopItem(shopItem: ShopItem) = viewModelScope.launch {
        _resumeState.update {
            it.copy(
                totPriceAmount = it.totPriceAmount + shopItem.price,
                totItemsInList = it.totItemsInList + 1
            )
        }
        shopListDao.upsertShopItem(shopItem = shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) = viewModelScope.launch {
        shopListDao.deleteShopItem(shopItem = shopItem)
        _resumeState.update {
            it.copy(
                totPriceAmount = it.totPriceAmount - shopItem.price,
                totItemsInList = it.totItemsInList - 1
            )
        }
    }
}

data class ShopItemResumeState(
    val totItemsInList: Int = 0,
    val totPriceAmount: Float = 0.0f
)
