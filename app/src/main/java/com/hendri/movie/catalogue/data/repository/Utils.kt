package com.hendri.movie.catalogue.data.repository

import android.app.AlertDialog
import android.content.Context
import androidx.paging.PagedList
import com.hendri.movie.catalogue.R

object Utils {
    fun config(pageSize: Int = 5): PagedList.Config =
        PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(pageSize).build()

    fun confirmDialog(context: Context, title: String?, isFavorite: Boolean, `fun`: () -> Unit) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage("Do you want to ${if (isFavorite) "delete" else "add"} $title ${if (isFavorite) "from" else "to"} favorite?")
            .setNegativeButton(context.getString(R.string.cancel), null)
            .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                `fun`.invoke()
            }.show()
    }
}