package com.rahulpundhir.ecomm.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulpundhir.ecomm.data.repository.ShopRepository


class ProductListViewModelFactory(
    private val categoryId: String,
    private val categoryName: String,
    private val shopRepository: ShopRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(categoryId, categoryName, shopRepository) as T
    }
}