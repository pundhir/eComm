package com.rahulpundhir.ecomm.data.network.response.productlist


data class ProductResult(
    val averageStars: Int,
    val brandId: String,
    val brandNameLong: String,
    val defaultVariationValue: DefaultVariationValue,
    val imageUris: List<String>,
    val nameShort: String,
    val noShippingCosts: Boolean,
    val notAllowedInCountry: Boolean,
    val priceLabel: String,
    val priceValidToTimestamp: Long,
    val productPrice: ProductPrice,
    val referencePriceLabel: String,
    val salesDrivers: List<Any>,
    val sku: String,
    val status: String
)