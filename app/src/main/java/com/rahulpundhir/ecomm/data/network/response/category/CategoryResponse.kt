package com.rahulpundhir.ecomm.data.network.response.category


data class CategoryResponse(
    val categoryId: Any,
    val children: List<Children>,
    val displayName: Any,
    val resultCount: Any
)