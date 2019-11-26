package com.rahulpundhir.ecomm.ui.util

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AlertDialog

object Utilities {

    fun showProductDescription(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setCancelable(false)
            .setTitle(title)
            .setMessage(Html.fromHtml(message))
            .setPositiveButton(
                "Ok", DialogInterface.OnClickListener { dialog, which -> }).show()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        var connected = false
        try {
            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connectivityManager.activeNetworkInfo
            connected = networkInfo != null && networkInfo.isAvailable &&
                    networkInfo.isConnected
            return connected

        } catch (e: Exception) {
            Log.v("connectivity", e.toString())
        }

        return connected
    }
}