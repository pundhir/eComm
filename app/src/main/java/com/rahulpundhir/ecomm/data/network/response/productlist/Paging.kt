package com.rahulpundhir.ecomm.data.network.response.productlist


import com.google.gson.annotations.SerializedName

data class Paging(
    val numPages: Int,
    val page: Int,
    val pageSize: Int
)