package com.rahulpundhir.ecomm.ui.product

import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.data.network.response.product.Variation
import com.rahulpundhir.ecomm.internal.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_product.*

class ProductVariantItem(val variant: Variation) : Item(){

    val PRICE_UNIT = " €"
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_productTitle.text = variant.variationType
            textView_priceLabel.text = variant.productPrice.priceLabel
            textView_price.text = variant.productPrice.price.toString() + PRICE_UNIT
            textView_referencePriceLabel.text = variant.productPrice.referencePriceLabel
            textView_referencePrice.text =
                variant.productPrice.referencePrice.toString() + PRICE_UNIT
            renderProductImage()
        }
    }

    override fun getLayout() = R.layout.item_product_variation

    private fun ViewHolder.renderProductImage() {
        if (!variant.imageUris.isEmpty()) {
            val baseUrl = "https://pic.hse24-dach.net/media/de/products/"
            val imageUri = variant.imageUris[0]
            val imageExt = "pics480.jpg"
            val imageUrl = baseUrl + imageUri + imageExt
            GlideApp.with(this.containerView)
                .load(imageUrl)
                .into(imageView_productImage)
        }
    }
}