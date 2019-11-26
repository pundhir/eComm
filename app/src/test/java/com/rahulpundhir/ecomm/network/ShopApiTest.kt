package com.rahulpundhir.ecomm.network

import com.rahulpundhir.ecomm.data.network.ConnectivityInterceptorImpl
import com.rahulpundhir.ecomm.data.network.LoggingInterceptorImpl
import com.rahulpundhir.ecomm.data.network.datasource.ShopApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class ShopApiTest {

    @Test
    @Throws
    internal fun testShopCategoryApi() = runBlockingTest {

       val result = ShopApiService
           .invoke(ConnectivityInterceptorImpl(RuntimeEnvironment.systemContext),
               LoggingInterceptorImpl()).getShopCategories().onAwait
        Assert.assertNotNull(result)
    }


    @Test
    @Throws
    internal fun testProductListApi() = runBlockingTest {

        val result = ShopApiService
            .invoke(ConnectivityInterceptorImpl(RuntimeEnvironment.systemContext),
                LoggingInterceptorImpl()).getProductList("17298261", "Haus&Garten").onAwait
        Assert.assertNotNull(result)
    }


    @Test
    @Throws
    internal fun testProductDetailsApi() = runBlockingTest {

        val result = ShopApiService
            .invoke(ConnectivityInterceptorImpl(RuntimeEnvironment.systemContext),
                LoggingInterceptorImpl()).getProductDetails("425209001").onAwait
        Assert.assertNotNull(result)
    }
}

