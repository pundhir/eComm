package com.rahulpundhir.ecomm.data.network.response.productlist

data class Filter(
    val filterGroups: List<FilterGroup>,
    val resetLink: String,
    val selectedFilterItems: List<Any>,
    val selectedItemCount: Int
)