package com.rahulpundhir.ecomm.data.network.response.productlist

data class Brand(
    val brandId: String,
    val children: Any,
    val displayName: String,
    val resultCount: Int
)