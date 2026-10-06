package com.application.e_commerce.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.e_commerce.data.model.CartRequest
import com.application.e_commerce.data.model.Product
import com.application.e_commerce.data.repository.CartRepository
import com.application.e_commerce.data.repository.ProductRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val productRepository = ProductRepository()
    private val cartRepository = CartRepository()

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _addToCartSuccess = MutableLiveData<Boolean>()
    val addToCartSuccess: LiveData<Boolean> = _addToCartSuccess

    fun loadProductDetail(productId: String) {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            val result = productRepository.getProducts()
            result.onSuccess { products ->
                val foundProduct = products.find { it.id == productId }
                if (foundProduct != null) {
                    _product.value = foundProduct!!
                } else {
                    _errorMessage.value = "Produk tidak ditemukan"
                }
            }.onFailure {
                _errorMessage.value = it.message
            }
            _isLoading.value = false
        }
    }

    fun addToCart(product: Product) {
        _isLoading.value = true
        viewModelScope.launch {
            val request = CartRequest(
                productId = product.id,
                name = product.name,
                price = product.price,
                imgUrl = product.imgUrl,
                quantity = 1
            )
            val result = cartRepository.addToCart(request)
            result.onSuccess {
                _addToCartSuccess.value = true
            }.onFailure {
                _errorMessage.value = it.message
            }
            _isLoading.value = false
        }
    }
}