package com.rahulpundhir.ecomm.data.network.response.product

data class Variation(
    val dimensions: Dimensions,
    val imageUris: List<String>,
    val picCount: Int,
    val productPrice: ProductPriceX,
    val sku: String,
    val status: String,
    val stockAmount: Int,
    val stockColor: String,
    val variationType: String
)