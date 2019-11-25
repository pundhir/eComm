package com.rahulpundhir.ecomm.data.network.response.productlist


import com.google.gson.annotations.SerializedName

data class FilterItem(
    val childCount: Int,
    val displayName: String,
    val filterName: String,
    val filterValue: String,
    val iconImgUrl: Any,
    val id: Any,
    val level: Int,
    val link: String,
    val name: String,
    val resetLink: String,
    val resultCount: Int,
    val rgbCode: Any,
    val selected: Boolean
)