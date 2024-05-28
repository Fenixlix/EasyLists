package com.example.easylists.screen_shop_multi_item_list.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItem
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItemScreenState
import com.example.easylists.screen_shop_multi_item_list.model.db_components.DbShopMultiItemListDao
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
class ShopMultiItemListViewModel @Inject constructor(
    private val shopMultiItemListDao: DbShopMultiItemListDao
) : ViewModel() {

    private val _shopMultiItemList = MutableStateFlow<List<ShopMultiItem>>(emptyList())
    private val _screenState = MutableStateFlow(ShopMultiItemScreenState())
    private var totalItems = 0
    private var totalPrice = 0.0f
    val promotion = listOf(1f, 0.85f, 0.80f, 0.75f, 0.50f, 0.25f)

    val state = combine(_screenState, _shopMultiItemList) { state, itemList ->
        state.itemName.clearText()
        state.itemPrice.edit {
            replace(0, length, "0.0")
        }
        state.copy(
            itemList = itemList,
            totalItems = totalItems,
            totalPrice = totalPrice
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ShopMultiItemScreenState()
    )

    init {
        viewModelScope.launch {
            shopMultiItemListDao.getShopMultiItemList().collect {
                if (totalItems == 0 && totalPrice == 0.0f) calculateTotals(it)
                _shopMultiItemList.value = it
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

    private fun calculateTotals(itemList: List<ShopMultiItem>) {
        var priceVessel = 0.0f
        var quantityVessel = 0
        itemList.forEach {
            priceVessel += it.price * it.quantity
            quantityVessel += it.quantity
        }
        totalPrice = priceVessel
        totalItems = quantityVessel
    }

    fun updatePrice(shopItem: ShopMultiItem, previousPrice: Float) = viewModelScope.launch {
        totalPrice += shopItem.price * shopItem.quantity - previousPrice * shopItem.quantity
        shopMultiItemListDao.upsertShopMultiItem(item = shopItem)
    }

    fun upDownCount(shopItem: ShopMultiItem, isUpCount: Boolean) = viewModelScope.launch {
        if (isUpCount) {
            totalPrice += shopItem.price
            totalItems++
            shopMultiItemListDao.upsertShopMultiItem(
                shopItem.copy(
                    quantity = shopItem.quantity + 1
                )
            )
        } else if (shopItem.quantity <= 1) {
            deleteShopMultiItem(shopItem)
        } else {
            totalPrice -= shopItem.price
            totalItems--
            shopMultiItemListDao.upsertShopMultiItem(
                shopItem.copy(
                    quantity = shopItem.quantity - 1
                )
            )
        }
    }

    private fun getPrice() = _screenState.value.itemPrice.text.toString().toFloat()
    private fun getName() = _screenState.value.itemName.text.toString()
    private fun getFinalPrice() = getPrice() * promotion[_screenState.value.discountPointer]
    fun upsertShopMultiItem() = viewModelScope.launch {
        val item = ShopMultiItem(
            name = getName(),
            price = getFinalPrice(),
            quantity = 1,
            promotionCode = _screenState.value.discountPointer
        )
        totalPrice += item.price
        shopMultiItemListDao.upsertShopMultiItem(item)
    }

    private fun deleteShopMultiItem(item: ShopMultiItem) = viewModelScope.launch {
        totalItems--
        totalPrice -= item.price
        shopMultiItemListDao.deleteShopMultiItem(item)
    }
}
