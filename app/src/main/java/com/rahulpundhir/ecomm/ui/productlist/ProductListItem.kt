package com.rahulpundhir.ecomm.ui.productlist

import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductResult
import com.rahulpundhir.ecomm.internal.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_product.*

class ProductListItem(val listItem: ProductResult) : Item() {
    val PRICE_UNIT = " €"
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_productTitle.text = listItem.nameShort
            textView_priceLabel.text = listItem.priceLabel
            textView_price.text = listItem.productPrice.price.toString() + PRICE_UNIT
            textView_referencePriceLabel.text = listItem.referencePriceLabel
            textView_referencePrice.text =
                listItem.productPrice.referencePrice.toString() + PRICE_UNIT
            renderProductImage()
        }
    }

    override fun getLayout() = R.layout.item_product

    private fun ViewHolder.renderProductImage() {
        if (!listItem.imageUris.isEmpty()) {
            val baseUrl = "https://pic.hse24-dach.net/media/de/products/"
            val imageUri = listItem.imageUris[0]
            val imageExt = "pics480.jpg"
            val imageUrl = baseUrl + imageUri + imageExt
            GlideApp.with(this.containerView)
                .load(imageUrl)
                .into(imageView_productImage)
        }
    }
}