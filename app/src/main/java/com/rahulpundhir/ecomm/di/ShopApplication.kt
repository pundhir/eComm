package com.rahulpundhir.ecomm.di

import android.app.Application
import android.content.Context
import com.rahulpundhir.ecomm.data.network.ConnectivityInterceptor
import com.rahulpundhir.ecomm.data.network.ConnectivityInterceptorImpl
import com.rahulpundhir.ecomm.data.network.LoggingInterceptor
import com.rahulpundhir.ecomm.data.network.LoggingInterceptorImpl
import com.rahulpundhir.ecomm.data.network.datasource.ShopApiService
import com.rahulpundhir.ecomm.data.network.datasource.ShopDataSource
import com.rahulpundhir.ecomm.data.network.datasource.ShopDataSourceImpl
import com.rahulpundhir.ecomm.data.repository.ShopRepository
import com.rahulpundhir.ecomm.data.repository.ShopRepositoryImpl
import com.rahulpundhir.ecomm.ui.category.ShopCategoryViewModelFactory
import com.rahulpundhir.ecomm.ui.product.ProductDetailViewModelFactory
import com.rahulpundhir.ecomm.ui.productlist.ProductListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class ShopApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ShopApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<LoggingInterceptor>() with singleton { LoggingInterceptorImpl() }
        bind() from singleton { ShopApiService(instance(), instance()) }
        bind<ShopDataSource>() with singleton { ShopDataSourceImpl(instance()) }
        bind<ShopRepository>() with singleton { ShopRepositoryImpl(instance()) }
        bind() from provider { ShopCategoryViewModelFactory(instance()) }
        bind() from factory { categoryId: String, categoryName: String ->
            ProductListViewModelFactory(
                categoryId,
                categoryName,
                instance()
            )
        }
        bind() from factory { productId: String ->
            ProductDetailViewModelFactory(
                productId,
                instance()
            )
        }
    }
}