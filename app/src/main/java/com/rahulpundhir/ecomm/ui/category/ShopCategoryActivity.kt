package com.rahulpundhir.ecomm.ui.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.ui.base.BaseActivity
import com.rahulpundhir.ecomm.ui.util.addFragment

class ShopCategoryActivity : BaseActivity() {
    override fun onNetworkConnected() {
        hideSnackbar()
    }

    override fun onNetworkDisconnected() {
        showSnackbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        addFragment(ShopCategoryFragment.newInstance(), R.id.fragment_container)
    }
}
