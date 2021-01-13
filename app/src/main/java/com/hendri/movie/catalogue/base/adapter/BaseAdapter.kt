package com.hendri.movie.catalogue.base.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Entity : Parcelable, ItemBinding : ViewDataBinding>(
    private val itemLayout: Int,
    diffUtil: DiffUtil.ItemCallback<Entity>,
    private val onItemClick: ItemListener<Entity>? = null
) :
    PagedListAdapter<Entity, BaseAdapter<Entity, ItemBinding>.Holder<ItemBinding>>(diffUtil) {

    var onItemListener: ItemListener<Entity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<ItemBinding> {
        return Holder(LayoutInflater.from(parent.context).inflate(itemLayout, parent, false))
    }

    inner class Holder<ItemBinding : ViewDataBinding>(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var binding = DataBindingUtil.bind<ItemBinding>(itemView)
    }
}