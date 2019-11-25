package com.rahulpundhir.ecomm.data.network.datasource

import androidx.lifecycle.LiveData
import com.rahulpundhir.ecomm.data.network.response.category.CategoryResponse
import com.rahulpundhir.ecomm.data.network.response.product.ProductResponse
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductListResponse
import retrofit2.Response

interface ShopDataSource {
    val requestShopCategories: LiveData<Response<CategoryResponse>>
    val requestProductList: LiveData<Response<ProductListResponse>>
    val requestProduct: LiveData<Response<ProductResponse>>

    suspend fun getShopCategories()
    suspend fun getProductList(categoryId: String, categoryName: String)
    suspend fun getProductDetails(pid: String)
}