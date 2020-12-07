package com.hendri.movie.catalogue.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.adapter.BaseAdapter
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.databinding.ItemContainerMovieBinding

class MovieAdapter : BaseAdapter<Movie, ItemContainerMovieBinding>(R.layout.item_container_movie, diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: Holder<ItemContainerMovieBinding>, position: Int) {
        holder.binding?.let { bind ->
            getItem(position)?.apply {
                bind.model = this
                bind.root.setOnClickListener { onItemListener?.onItemClick(this) }
            }
        }
    }
}