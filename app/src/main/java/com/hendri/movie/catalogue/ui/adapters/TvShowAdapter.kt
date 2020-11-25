package com.hendri.movie.catalogue.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.databinding.ItemContainerTvShowsBinding
import com.hendri.movie.catalogue.ui.listeners.TvShowListener

class TvShowAdapter(private val itemListener: TvShowListener): RecyclerView.Adapter<TvShowAdapter.MovieViewHolder>() {

    private lateinit var tvShows: List<TvShow>
    private lateinit var layoutInflater: LayoutInflater

    fun setData(tvShows: List<TvShow>) {
        this.tvShows = tvShows
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding: ItemContainerTvShowsBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_container_tv_shows, parent, false
        )
        return MovieViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovies(tvShows[position])
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class MovieViewHolder(itemBinding: ItemContainerTvShowsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val itemContainerBinding: ItemContainerTvShowsBinding = itemBinding

        fun bindMovies(tvShow: TvShow) {
            itemContainerBinding.model = tvShow
            itemContainerBinding.executePendingBindings()

            itemContainerBinding.root.setOnClickListener {
                itemListener.onItemClicked(tvShow)
            }
        }
    }

}