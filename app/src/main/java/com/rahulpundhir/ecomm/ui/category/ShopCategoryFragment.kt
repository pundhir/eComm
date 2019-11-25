package com.rahulpundhir.ecomm.ui.category

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
import com.rahulpundhir.ecomm.ui.base.ScopedFragment
import com.rahulpundhir.ecomm.ui.productlist.ProductListActivity
import com.rahulpundhir.ecomm.ui.productlist.ProductListFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.shop_category_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.net.URLEncoder

class ShopCategoryFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: ShopCategoryViewModelFactory by instance()

    companion object {
        fun newInstance() = ShopCategoryFragment()
    }

    private lateinit var viewModel: ShopCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shop_category_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ShopCategoryViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val categoryData = viewModel.categoryData.await()
        categoryData.observe(viewLifecycleOwner, Observer { categoryResponse ->
            if (categoryResponse == null) {
                return@Observer
            }
            group_loading.visibility = View.GONE
            initRecyclerView(categoryResponse.children.toShopCategoryItems())
        })
    }

    private fun List<Children>.toShopCategoryItems(): List<ShopCategoryItem> {
        return this.map { ShopCategoryItem(it) }
    }

    private fun initRecyclerView(items: List<ShopCategoryItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView_category.apply {
            layoutManager = LinearLayoutManager(this@ShopCategoryFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? ShopCategoryItem)?.let {
                showCategoryProductList(it.categoryItem.categoryId, it.categoryItem.displayName)
            }
        }
    }

    private fun showCategoryProductList(catId: Int, catName: String) {
        val bundle = Bundle()
        bundle.putString(ProductListFragment.INTENT_ARG_CATEGORY_ID, catId.toString())
        var category = URLEncoder.encode(catName, "utf-8");
        bundle.putString(ProductListFragment.INTENT_ARG_CATEGORY_NAME, category)
        ProductListActivity.start(this@ShopCategoryFragment.context!!, bundle)
    }
}
