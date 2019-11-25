package com.rahulpundhir.ecomm.ui.util

import android.content.Context
import android.content.DialogInterface
import android.text.Html
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
}