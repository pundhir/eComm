package com.rahulpundhir.ecomm.data.network.response.productlist


import com.google.gson.annotations.SerializedName

data class Filter(
    val filterGroups: List<FilterGroup>,
    val resetLink: String,
    val selectedFilterItems: List<Any>,
    val selectedItemCount: Int
)