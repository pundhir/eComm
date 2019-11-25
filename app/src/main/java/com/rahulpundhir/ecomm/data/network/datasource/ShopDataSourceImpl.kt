package com.rahulpundhir.ecomm.data.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rahulpundhir.ecomm.data.network.response.category.CategoryResponse
import com.rahulpundhir.ecomm.data.network.response.product.ProductResponse
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductListResponse
import com.rahulpundhir.ecomm.internal.NoConnectivityException
import retrofit2.Response
import java.lang.Exception

class ShopDataSourceImpl(private val apiService: ShopApiService) :
    ShopDataSource {

    private val _requestShopCategories = MutableLiveData<Response<CategoryResponse>>()
    private val _requestProductList = MutableLiveData<Response<ProductListResponse>>()
    private val _requestShopProduct = MutableLiveData<Response<ProductResponse>>()


    override val requestShopCategories: LiveData<Response<CategoryResponse>>
        get() = _requestShopCategories

    override suspend fun getShopCategories() {
        try {
            val categories = apiService.getShopCategories().await()
            _requestShopCategories.postValue(categories)
        } catch (exception: NoConnectivityException) {
            Log.e("Connectivity", "No Internet connectivity." + exception)
        } catch (exception: Exception) {
            Log.e("Http Exception", "Exception = " + exception.cause)
        }
    }

    override val requestProductList: LiveData<Response<ProductListResponse>>
        get() = _requestProductList

    override suspend fun getProductList(categoryId: String, categoryName: String) {
        try {
            val productList = apiService.getProductList(categoryId, categoryName).await()
            _requestProductList.postValue(productList)
        } catch (exception: NoConnectivityException) {
            Log.e("Connectivity", "No Internet connectivity." + exception)
        } catch (exception: Exception) {
            Log.e("Http Exception", "Exception = " + exception.cause)
        }
    }

    override val requestProduct: LiveData<Response<ProductResponse>>
        get() = _requestShopProduct

    override suspend fun getProductDetails(pid: String) {
        try {
            val productDetail = apiService.getProductDetails(pid).await()
            _requestShopProduct.postValue(productDetail)
        } catch (exception: NoConnectivityException) {
            Log.e("Connectivity", "No Internet connectivity." + exception)
        } catch (exception: Exception) {
            Log.e("Http Exception", "Exception = " + exception.cause)
        }
    }
}