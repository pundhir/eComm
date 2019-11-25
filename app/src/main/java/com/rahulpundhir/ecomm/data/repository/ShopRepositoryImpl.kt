package com.rahulpundhir.ecomm.data.repository

import androidx.lifecycle.LiveData
import com.rahulpundhir.ecomm.data.network.datasource.ShopDataSource
import com.rahulpundhir.ecomm.data.network.response.category.CategoryResponse
import com.rahulpundhir.ecomm.data.network.response.product.ProductResponse
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ShopRepositoryImpl(private val shopDataSource: ShopDataSource) :
    ShopRepository {

    override suspend fun getShopCategories(): LiveData<Response<CategoryResponse>> {
        return withContext(Dispatchers.IO) {
            fetchShopCategory()
            return@withContext shopDataSource.requestShopCategories
        }
    }

    override suspend fun getProductList(categoryId: String, categoryName: String): LiveData<Response<ProductListResponse>> {

        return withContext(Dispatchers.IO) {
            fetchProductList(categoryId, categoryName)
            return@withContext shopDataSource.requestProductList
        }

    }

    override suspend fun getProductDetail(pId: String): LiveData<Response<ProductResponse>> {

        return withContext(Dispatchers.IO) {
            fetchProductDetail(pId)
            return@withContext shopDataSource.requestProduct
        }

    }

    private suspend fun fetchShopCategory() {
        shopDataSource.getShopCategories()
    }

    private suspend fun fetchProductList(categoryId: String, categoryName: String) {
        shopDataSource.getProductList(categoryId, categoryName)
    }

    private suspend fun fetchProductDetail(pId: String) {
        shopDataSource.getProductDetails(pId)
    }
}