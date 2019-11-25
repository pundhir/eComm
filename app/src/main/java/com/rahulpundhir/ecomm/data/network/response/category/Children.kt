package com.rahulpundhir.ecomm.data.network.response.category


data class Children(
    val categoryId: Int,
    val children: List<ChildrenX>,
    val displayName: String,
    val resultCount: Any
)