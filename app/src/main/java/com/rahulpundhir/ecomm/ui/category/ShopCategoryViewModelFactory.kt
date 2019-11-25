package com.rahulpundhir.ecomm.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulpundhir.ecomm.data.repository.ShopRepository

class ShopCategoryViewModelFactory(private val shopRepository: ShopRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShopCategoryViewModel(shopRepository) as T
    }
}