package com.rahulpundhir.ecomm.ui.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.ui.util.addFragment

class ShopCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        addFragment(ShopCategoryFragment.newInstance(), R.id.fragment_container)
    }
}
