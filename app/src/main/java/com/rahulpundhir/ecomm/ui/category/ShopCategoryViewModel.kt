package com.rahulpundhir.ecomm.ui.category

import androidx.lifecycle.ViewModel
import com.rahulpundhir.ecomm.internal.lazyDeferred
import com.rahulpundhir.ecomm.data.repository.ShopRepository

class ShopCategoryViewModel(private val shopRepository: ShopRepository) :
    ViewModel() {

    val categoryData by lazyDeferred {
        shopRepository.getShopCategories()
    }
}
