package com.application.e_commerce.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.e_commerce.data.model.Product
import com.application.e_commerce.data.repository.ProductRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = ProductRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private var allProducts: List<Product> = emptyList()

    fun loadProducts() {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            val result = repository.getProducts()
            result.onSuccess {
                allProducts = it
                _products.value = it
            }.onFailure {
                _errorMessage.value = it.message
            }
            _isLoading.value = false
        }
    }

    fun searchProducts(query: String) {
        if (query.isEmpty()) {
            _products.value = allProducts
        } else {
            val filteredList = allProducts.filter {
                it.name.contains(query, ignoreCase = true) || 
                it.category.contains(query, ignoreCase = true)
            }
            _products.value = filteredList
        }
    }
}