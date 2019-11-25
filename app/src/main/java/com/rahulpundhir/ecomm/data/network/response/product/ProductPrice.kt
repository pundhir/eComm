package com.rahulpundhir.ecomm.data.network.response.product

data class ProductPrice(
    val basePrice: Any,
    val basePriceInfo: Any,
    val basePriceRefAmount: Any,
    val basePriceUnit: Any,
    val country: String,
    val currency: String,
    val percentDiscount: Any,
    val price: Double,
    val priceDiscount: Double,
    val priceLabel: String,
    val priceValidTo: String,
    val referencePrice: Double,
    val referencePriceLabel: String,
    val shippingCosts: Double
)