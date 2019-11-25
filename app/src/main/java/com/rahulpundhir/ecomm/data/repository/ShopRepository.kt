package com.rahulpundhir.ecomm.data.repository

import androidx.lifecycle.LiveData
import com.rahulpundhir.ecomm.data.network.response.category.CategoryResponse
import com.rahulpundhir.ecomm.data.network.response.product.ProductResponse
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductListResponse
import retrofit2.Response

interface ShopRepository {
    suspend fun getShopCategories(): LiveData<Response<CategoryResponse>>

    suspend fun getProductList(categoryId: String, categoryName: String):
            LiveData<Response<ProductListResponse>>

    suspend fun getProductDetail(pId: String): LiveData<Response<ProductResponse>>
}