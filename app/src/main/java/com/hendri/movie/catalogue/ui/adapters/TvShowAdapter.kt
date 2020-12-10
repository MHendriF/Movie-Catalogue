package com.hendri.movie.catalogue.ui.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.adapter.BaseAdapter
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.databinding.ItemContainerTvShowBinding

class TvShowAdapter : BaseAdapter<TvShow, ItemContainerTvShowBinding>(R.layout.item_container_tv_show, diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: Holder<ItemContainerTvShowBinding>, position: Int) {
        holder.binding?.let { bind ->
            getItem(position)?.apply {
                bind.model = this
                bind.root.setOnClickListener { onItemListener?.onItemClick(this) }
            }
        }
    }
}