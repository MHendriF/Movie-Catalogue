package com.hendri.movie.catalogue.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.databinding.ItemContainerTvShowBinding
import com.hendri.movie.catalogue.ui.listeners.TvShowListener

class TvShowAdapter( private val tvShows: List<DataEntity>,  private val tvShowsListener: TvShowListener) :
    RecyclerView.Adapter<TvShowAdapter.TVShowViewHolder>() {

    //private val tvShows: List<DataEntity> = tvShows
    private var layoutInflater: LayoutInflater? = null
    //private val tvShowsListener: TvShowListener = tvShowsListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val tvShowBinding: ItemContainerTvShowBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.item_container_tv_show, parent, false
        )
        return TVShowViewHolder(tvShowBinding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bindTVShow(tvShows[position])
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class TVShowViewHolder(itemContainerTvShowBinding: ItemContainerTvShowBinding) :
        RecyclerView.ViewHolder(itemContainerTvShowBinding.root) {
        private val itemContainerTvShowBinding: ItemContainerTvShowBinding =
            itemContainerTvShowBinding

        fun bindTVShow(tvShow: DataEntity) {
            itemContainerTvShowBinding.tvShow = tvShow
            itemContainerTvShowBinding.executePendingBindings()
            itemContainerTvShowBinding.root.setOnClickListener { view ->
                tvShowsListener.onTVShowClicked(
                    tvShow
                )
            }
        }

    }

}
