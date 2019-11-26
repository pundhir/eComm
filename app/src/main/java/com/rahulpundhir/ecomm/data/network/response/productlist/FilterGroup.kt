package com.rahulpundhir.ecomm.data.network.response.productlist

data class FilterGroup(
    val displayName: String,
    val displayType: Any,
    val fieldName: String,
    val filterItems: List<FilterItem>,
    val filterName: String,
    val hidden: Boolean,
    val name: String,
    val resetLink: String
)