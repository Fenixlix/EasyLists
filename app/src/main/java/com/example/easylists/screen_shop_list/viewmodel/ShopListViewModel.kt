package com.example.easylists.screen_shop_list.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_shop_list.model.data_types.ShopItem
import com.example.easylists.screen_shop_list.model.data_types.ShopItemScreenState
import com.example.easylists.screen_shop_list.model.db_components.DbShopListDao
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
class ShopListViewModel @Inject constructor(
    private val shopListDao: DbShopListDao
) : ViewModel() {

    private val _shopList = MutableStateFlow<List<ShopItem>>(emptyList())
    private val _screenState = MutableStateFlow(ShopItemScreenState())
    private var totalPrice = 0.0f
    val promotion = listOf(1f, 0.85f, 0.80f, 0.75f, 0.50f, 0.25f)

    val state = combine(_screenState, _shopList) { state, itemList ->
        state.itemName.clearText()
        state.itemPrice.edit {
            replace(0, length, "0.0")
        }
        state.copy(
            itemList = itemList,
            totalItems = itemList.size,
            totalPrice = totalPrice
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ShopItemScreenState()
    )

    init {
        viewModelScope.launch {
            shopListDao.getShopList().collect { shopItemList ->
                if (totalPrice == 0.0f) {
                    shopItemList.forEach {
                        totalPrice += it.price
                    }
                }
                _shopList.value = shopItemList
            }
        }
    }

    fun toggleDialog() {
        _screenState.update {
            it.copy(
                showDialog = !it.showDialog
            )
        }
    }

    fun changePromotion(selectedPromotion: Int) {
        _screenState.update {
            it.copy(
                discountPointer = selectedPromotion
            )
        }
    }

    fun updateShopItemPrice(shopItem: ShopItem, previousPrice: Float) = viewModelScope.launch {
        totalPrice += shopItem.price - previousPrice
        shopListDao.upsertShopItem(shopItem = shopItem)
    }

    private fun getPrice() = _screenState.value.itemPrice.text.toString().toFloat()
    private fun getName() = _screenState.value.itemName.text.toString()
    private fun getFinalPrice() = getPrice() * promotion[_screenState.value.discountPointer]
    fun upsertShopItem() = viewModelScope.launch {
        val shopItem = ShopItem(
            name = getName(),
            price = getFinalPrice(),
            promotionCode = _screenState.value.discountPointer
        )
        totalPrice += shopItem.price
        shopListDao.upsertShopItem(shopItem = shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) = viewModelScope.launch {
        totalPrice -= shopItem.price
        shopListDao.deleteShopItem(shopItem = shopItem)
    }
}