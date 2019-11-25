package com.rahulpundhir.ecomm.data.network.response.productlist


import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    val activeSorting: ActiveSorting,
    val brands: List<Brand>,
    val cachingForbidden: Boolean,
    val categories: List<Any>,
    val displayName: Any,
    val filter: Filter,
    val paging: Paging,
    val productResults: List<ProductResult>,
    val resultCount: Int,
    val sortings: List<Sorting>,
    val topShop: String
)