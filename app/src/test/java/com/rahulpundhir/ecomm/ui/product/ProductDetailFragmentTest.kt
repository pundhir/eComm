package com.rahulpundhir.ecomm.ui.product

import org.junit.Before
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.factory

class ProductDetailFragmentTest: KodeinAware {
    override val kodein: Kodein
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

//    override val kodein by closestKodein()

    private val viewModelFactory: ((String) -> ProductDetailViewModelFactory) by factory()

    private lateinit var viewModel: ProductDetailViewModel

    @Before
    fun setUp() {
//        viewModel = ViewModelProvider1(this, viewModelFactory(""))
//            .get(ProductDetailViewModel::class.java)
    }
}