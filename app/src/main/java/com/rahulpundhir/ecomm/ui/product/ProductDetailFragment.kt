package com.rahulpundhir.ecomm.ui.product

import android.content.ClipDescription
import android.content.DialogInterface
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.data.network.response.product.Variation
import com.rahulpundhir.ecomm.internal.GlideApp
import com.rahulpundhir.ecomm.ui.base.ScopedFragment
import com.rahulpundhir.ecomm.ui.productlist.ProductListItem
import com.rahulpundhir.ecomm.ui.util.Utilities
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.product_detail_fragment.*
import kotlinx.android.synthetic.main.product_detail_fragment.group_loading
import kotlinx.android.synthetic.main.product_detail_fragment.imageView_productImage
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class ProductDetailFragment : ScopedFragment(), KodeinAware, View.OnClickListener {

    override val kodein by closestKodein()
    lateinit var productDescription: String

    private val viewModelFactory: ((String) -> ProductDetailViewModelFactory) by factory()

    companion object {
        val INTENT_ARG_PRODUCT_ID = "INTENT_ARG_PRODUCT_ID"
        fun newInstance() = ProductDetailFragment()
    }

    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val productId = arguments?.getString(INTENT_ARG_PRODUCT_ID)!!
        viewModel = ViewModelProvider(this, viewModelFactory(productId))
            .get(ProductDetailViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val productDetails = viewModel.productDetails.await()
        productDetails.observe(viewLifecycleOwner, Observer { productResponse ->
            if (productResponse ==  null) {
                return@Observer
            }

            if (productResponse.isSuccessful) {
                group_loading.visibility = View.GONE
                group_display_page.visibility = View.VISIBLE
                val data = productResponse.body()
                data?.let {
                    productDescription = it.longDescription
                    if (!it.imageUris.isEmpty()) {
                        renderProductImage(it.imageUris[0])
                    }
                    textView_productDesc.text = it.nameShort
                    textView_productDesc.setOnClickListener(this@ProductDetailFragment)
                    ratingBar_productRating.rating = it.averageStars
                    textView_numOfRatings.text = "(" + it.reviewers + ")"

                    if (it.variations.isEmpty()) {
                        // TODO - error handling
                    } else {
                        initRecyclerView(it.variations.toProductVariants())
                    }
                }
            } else {
                // TODO - error handling)
            }
        })
    }

    private fun renderProductImage(imageUri: String) {
        val baseUrl = "https://pic.hse24-dach.net/media/de/products/"
        val imageExt = "pics480.jpg"
        val imageUrl = baseUrl + imageUri + imageExt
        GlideApp.with(this@ProductDetailFragment)
            .load(imageUrl)
            .into(imageView_productImage)
    }

    private fun List<Variation>.toProductVariants(): List<ProductVariantItem> {
        return this.map { ProductVariantItem(it) }
    }

    private fun initRecyclerView(items: List<ProductVariantItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView_productVariation.apply {
            layoutManager = LinearLayoutManager(this@ProductDetailFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? ProductVariantItem)?.let {
                Toast.makeText(
                    this@ProductDetailFragment.context,
                    "Cart implementation is in progress....",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onClick(view: View?) {
        when {
            view?.id == textView_productDesc.id -> {
                Utilities.showProductDescription(this@ProductDetailFragment.context!!,
                    "Product Description", productDescription)
            }
        }
    }
}
