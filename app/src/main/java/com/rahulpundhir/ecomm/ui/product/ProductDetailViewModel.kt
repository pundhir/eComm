package com.rahulpundhir.ecomm.ui.product

import androidx.lifecycle.ViewModel
import com.rahulpundhir.ecomm.data.repository.ShopRepository
import com.rahulpundhir.ecomm.internal.lazyDeferred

class ProductDetailViewModel(
    private val pId: String,
    private val shopRepository: ShopRepository
) :
    ViewModel() {

    val productDetails by lazyDeferred {
        shopRepository.getProductDetail(pId)
    }
}
