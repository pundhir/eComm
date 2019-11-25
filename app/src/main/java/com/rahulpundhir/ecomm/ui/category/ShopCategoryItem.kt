package com.rahulpundhir.ecomm.ui.category

import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.data.network.response.category.Children
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_category.*


class ShopCategoryItem(val categoryItem: Children) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_category.text = categoryItem.displayName
        }
    }

    override fun getLayout() = R.layout.item_category
}