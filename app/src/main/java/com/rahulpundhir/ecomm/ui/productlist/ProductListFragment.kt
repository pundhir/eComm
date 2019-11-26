package com.rahulpundhir.ecomm.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.data.network.response.category.Children
import com.rahulpundhir.ecomm.data.network.response.productlist.ProductResult
import com.rahulpundhir.ecomm.ui.base.ScopedFragment
import com.rahulpundhir.ecomm.ui.category.ShopCategoryItem
import com.rahulpundhir.ecomm.ui.product.ProductDetailActivity
import com.rahulpundhir.ecomm.ui.product.ProductDetailFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.product_detail_fragment.*
import kotlinx.android.synthetic.main.product_list_fragment.*
import kotlinx.android.synthetic.main.shop_category_fragment.*
import kotlinx.android.synthetic.main.shop_category_fragment.group_loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory
import org.kodein.di.generic.factory2


class ProductListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: ((String, String) -> ProductListViewModelFactory) by factory2()

    companion object {
        val INTENT_ARG_CATEGORY_ID = "INTENT_ARG_CATEGORY_ID"
        val INTENT_ARG_CATEGORY_NAME = "INTENT_ARG_CATEGORY_NAME"
        fun newInstance() = ProductListFragment()
    }

    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categoryId = arguments?.getString(INTENT_ARG_CATEGORY_ID)!!
        val categoryName = arguments?.getString(INTENT_ARG_CATEGORY_NAME)!!
        viewModel = ViewModelProvider(this, viewModelFactory(categoryId, categoryName))
            .get(ProductListViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val productList = viewModel.productListData.await()
        productList.observe(viewLifecycleOwner, Observer {productListResponse ->
            if (productListResponse ==  null) {
                return@Observer
            }

            if (productListResponse.isSuccessful) {
                group_loading.visibility = View.GONE
                val data = productListResponse.body()
                data?.let {
                    if (it.productResults.isEmpty()) {
                        textView_productList_error.visibility = View.VISIBLE
                    } else {
                        initRecyclerView(it.productResults.toProductListItems())
                    }
                }
            } else {
                textView_productList_error.visibility = View.VISIBLE
            }
        })
    }

    private fun List<ProductResult>.toProductListItems(): List<ProductListItem> {
        return this.map { ProductListItem(it) }
    }

    private fun initRecyclerView(items: List<ProductListItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView_productList.apply {
            layoutManager = LinearLayoutManager(this@ProductListFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? ProductListItem)?.let {
                showProductDetails(it.listItem.sku)
            }
        }
    }

    private fun showProductDetails(productId: String) {
        Toast.makeText(
            this@ProductListFragment.context,
            "Product Id = " + productId,
            Toast.LENGTH_SHORT
        ).show()
        val bundle = Bundle()
        bundle.putString(ProductDetailFragment.INTENT_ARG_PRODUCT_ID, productId)
        ProductDetailActivity.start(this@ProductListFragment.context!!, bundle)
    }
}
