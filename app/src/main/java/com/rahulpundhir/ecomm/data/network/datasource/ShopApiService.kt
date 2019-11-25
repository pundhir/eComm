package com.rahulpundhir.ecomm.data.network.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rahulpundhir.ecomm.BuildConfig
import com.rahulpundhir.ecomm.data.network.ConnectivityInterceptor
import com.rahulpundhir.ecomm.data.network.LoggingInterceptor
import com.rahulpundhir.ecomm.data.network.response.category.CategoryResponse
import com.rahulpundhir.ecomm.data.network.response.product.ProductResponse
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductListResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopApiService {

    @GET("category/tree")
    fun getShopCategories(): Deferred<CategoryResponse>

    @GET("c/**/*-{categoryId}/%3FfilterAttribute_motive%3D{categoryName}")
    fun getProductList(
        @Path("categoryId") categoryId: String,
        @Path("categoryName") categoryName: String
    ): Deferred<ProductListResponse>

    @GET("product/{pId}")
    fun getProductDetails(@Path("pId") productId: String): Deferred<ProductResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor,
            loggingInterceptor: LoggingInterceptor
        ):
                ShopApiService {
            val requestInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("appDevice", "IPAD")
                    .addHeader("locale", "de_DE")
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val builder = OkHttpClient.Builder()
            builder.addInterceptor(requestInterceptor)
            builder.addInterceptor(connectivityInterceptor)
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(loggingInterceptor)
            }
            return Retrofit.Builder()
                .client(builder.build())
                .baseUrl("https://www.hse24.de/ext-api/app/1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopApiService::class.java)
        }
    }
}