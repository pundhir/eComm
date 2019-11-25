package com.rahulpundhir.ecomm.data.network.response.category


data class ChildrenX(
    val categoryId: Int,
    val children: List<ChildrenXX>,
    val displayName: String,
    val resultCount: Any
)