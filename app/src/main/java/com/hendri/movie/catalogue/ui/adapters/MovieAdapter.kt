package com.hendri.movie.catalogue.ui.adapters

import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.adapter.BaseAdapter
import com.hendri.movie.catalogue.data.source.remote.response.the_movie_db.Movie
import com.hendri.movie.catalogue.databinding.ItemContainerMovieBinding


class MovieAdapter : BaseAdapter<Movie, ItemContainerMovieBinding>(R.layout.item_container_movie) {
    override fun onBindViewHolder(holder: Holder<ItemContainerMovieBinding>, position: Int) {
        holder.binding?.let { bind ->
            bind.model = data[position]
            bind.root.setOnClickListener { onItemListener?.onItemClick(data[position]) }
        }
    }
}