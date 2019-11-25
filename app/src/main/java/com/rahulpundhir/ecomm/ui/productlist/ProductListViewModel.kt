package com.rahulpundhir.ecomm.ui.productlist

import androidx.lifecycle.ViewModel
import com.rahulpundhir.ecomm.data.repository.ShopRepository
import com.rahulpundhir.ecomm.internal.lazyDeferred

class ProductListViewModel(
    private val catId: String,
    private val catName: String,
    private val shopRepository: ShopRepository
) :
    ViewModel() {

    val productListData by lazyDeferred {
        shopRepository.getProductList(catId, catName)
    }
}
