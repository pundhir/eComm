package com.rahulpundhir.ecomm.data.network.response.productlist


import com.google.gson.annotations.SerializedName

data class Sorting(
    val displayName: String,
    val name: String,
    val order: String,
    val sortType: String
)