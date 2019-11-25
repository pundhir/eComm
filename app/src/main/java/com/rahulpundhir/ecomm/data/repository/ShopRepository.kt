package com.rahulpundhir.ecomm.data.repository

import androidx.lifecycle.LiveData
import com.rahulpundhir.ecomm.data.network.response.category.CategoryResponse
import com.rahulpundhir.ecomm.data.network.response.product.ProductResponse
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductListResponse

interface ShopRepository {
    suspend fun getShopCategories(): LiveData<CategoryResponse>

    suspend fun getProductList(categoryId: String, categoryName: String):
            LiveData<ProductListResponse>

    suspend fun getProductDetail(pId: String): LiveData<ProductResponse>
}