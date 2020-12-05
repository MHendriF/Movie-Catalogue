package com.hendri.movie.catalogue.ui.adapters

import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.adapter.BaseAdapter
import com.hendri.movie.catalogue.data.source.remote.response.models.TvShow
import com.hendri.movie.catalogue.databinding.ItemContainerTvShowBinding

class TvShowAdapter : BaseAdapter<TvShow, ItemContainerTvShowBinding>(R.layout.item_container_tv_show) {
    override fun onBindViewHolder(holder: Holder<ItemContainerTvShowBinding>, position: Int) {
        holder.binding?.let { bind ->
            bind.model = data[position]
            bind.root.setOnClickListener { onItemListener?.onItemClick(data[position]) }
        }
    }
}