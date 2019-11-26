package com.rahulpundhir.ecomm.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rahulpundhir.ecomm.R
import com.rahulpundhir.ecomm.ui.util.Utilities

abstract class BaseActivity : AppCompatActivity() {

    lateinit var snackbar: Snackbar
    lateinit var messageView: View

    protected abstract fun onNetworkConnected()

    protected abstract fun onNetworkDisconnected()

    private fun initializeNetworkErrorSnackbar() {
        snackbar = makeErrorMessageSnackbar(
            messageView,
            resources.getString(R.string.connection_error_text)
        )

    }

    fun makeErrorMessageSnackbar(view: View, message: String): Snackbar {
        val errorMessageSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        val snackbarView = errorMessageSnackbar.view
        val textView = snackbarView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        textView.maxLines = 4
        return errorMessageSnackbar
    }


    protected fun showSnackbar() {
        snackbar.show()
    }


    protected fun hideSnackbar() {
        if (snackbar.isShown()) {
            snackbar.dismiss()
        }
    }

    var networkStatusBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (Utilities.isNetworkAvailable(context)) {
                onNetworkConnected()
            } else {
                onNetworkDisconnected()
            }
        }
    }

    private fun registerNetworkCheckReceiver() {
        val networkStatusFilter = IntentFilter()
        networkStatusFilter.addAction("android.net.wifi.STATE_CHANGE")
        networkStatusFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkStatusBroadcastReceiver, networkStatusFilter)
    }

    override fun onResume() {
        super.onResume()
        messageView = this.window.decorView.findViewById(android.R.id.content)
        initializeNetworkErrorSnackbar()
        registerNetworkCheckReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkStatusBroadcastReceiver)
    }
}