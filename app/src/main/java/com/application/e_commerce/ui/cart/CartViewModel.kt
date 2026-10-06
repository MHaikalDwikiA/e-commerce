package com.application.e_commerce.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.e_commerce.data.model.CartItem
import com.application.e_commerce.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val repository = CartRepository()

    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> = _cartItems

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = _totalPrice

    fun loadCart() {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            val result = repository.getCart()
            result.onSuccess {
                _cartItems.value = it
                calculateTotal(it)
            }.onFailure {
                _errorMessage.value = it.message
            }
            _isLoading.value = false
        }
    }

    fun updateQuantity(item: CartItem, newQuantity: Int) {
        if (newQuantity < 1) return
        if (item.id == null) return
        
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.updateCartQuantity(item.id, newQuantity)
            result.onSuccess { updatedItem ->
                val currentList = _cartItems.value?.toMutableList() ?: return@onSuccess
                val index = currentList.indexOfFirst { it.id == updatedItem.id }
                if (index != -1) {
                    currentList[index] = updatedItem
                    _cartItems.value = currentList
                    calculateTotal(currentList)
                }
            }.onFailure {
                _errorMessage.value = it.message
            }
            _isLoading.value = false
        }
    }

    fun deleteItem(item: CartItem) {
        if (item.id == null) return
        
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.deleteCartItem(item.id)
            result.onSuccess {
                val currentList = _cartItems.value?.toMutableList() ?: return@onSuccess
                currentList.removeAll { it.id == item.id }
                _cartItems.value = currentList
                calculateTotal(currentList)
            }.onFailure {
                _errorMessage.value = it.message
            }
            _isLoading.value = false
        }
    }

    private fun calculateTotal(items: List<CartItem>) {
        var total = 0.0
        for (item in items) {
            val price = item.price.toDoubleOrNull() ?: 0.0
            total += price * item.quantity
        }
        _totalPrice.value = total
    }
}