package com.rahulpundhir.ecomm.ui.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.ui.util.addFragment

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val fragment = ProductDetailFragment.newInstance()

        if (intent.extras != null) {
            fragment.arguments = intent.extras
        }
        addFragment(fragment, R.id.fragment_container)
    }

    companion object {
        fun start(context: Context, bundle: Bundle) {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}
