package com.rahulpundhir.ecomm.data.network.datasource

import androidx.lifecycle.LiveData
import com.rahulpundhir.ecomm.data.network.response.category.CategoryResponse
import com.rahulpundhir.ecomm.data.network.response.product.ProductResponse
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductListResponse

interface ShopDataSource {
    val requestShopCategories: LiveData<CategoryResponse>
    val requestProductList: LiveData<ProductListResponse>
    val requestProduct: LiveData<ProductResponse>

    suspend fun getShopCategories()
    suspend fun getProductList(categoryId: String, categoryName: String)
    suspend fun getProductDetails(pid: String)
}