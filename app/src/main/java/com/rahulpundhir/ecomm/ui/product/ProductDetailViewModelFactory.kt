package com.rahulpundhir.ecomm.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulpundhir.ecomm.data.repository.ShopRepository

class ProductDetailViewModelFactory(
    private val productId: String,
    private val shopRepository: ShopRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductDetailViewModel(productId, shopRepository) as T
    }
}