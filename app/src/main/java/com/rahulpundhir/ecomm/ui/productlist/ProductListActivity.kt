package com.rahulpundhir.ecomm.ui.productlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.ui.base.BaseActivity
import com.rahulpundhir.ecomm.ui.util.addFragment

class ProductListActivity : BaseActivity() {

    override fun onNetworkConnected() {
        hideSnackbar()
    }

    override fun onNetworkDisconnected() {
        showSnackbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val fragment = ProductListFragment.newInstance()

        if (intent.extras != null) {
            fragment.arguments = intent.extras
        }
        addFragment(fragment, R.id.fragment_container)
    }

    companion object {
        fun start(context: Context, bundle: Bundle) {
            val intent = Intent(context, ProductListActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}
