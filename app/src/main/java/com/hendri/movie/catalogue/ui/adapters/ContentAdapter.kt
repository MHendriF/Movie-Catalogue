package com.hendri.movie.catalogue.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.databinding.ItemContainerMovieBinding
import com.hendri.movie.catalogue.ui.listeners.ItemListener

class ContentAdapter(private val itemListener: ItemListener): RecyclerView.Adapter<ContentAdapter.MovieViewHolder>() {

    private lateinit var listDataEntity: List<DataEntity>
    private lateinit var layoutInflater: LayoutInflater

    fun setData(dataEntity: List<DataEntity>) {
        this.listDataEntity = dataEntity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val movieBinding: ItemContainerMovieBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_container_movie, parent, false
        )
        return MovieViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovies(listDataEntity[position])
    }

    override fun getItemCount(): Int {
        return listDataEntity.size
    }

    inner class MovieViewHolder(itemContainerMovieBinding: ItemContainerMovieBinding) :
        RecyclerView.ViewHolder(itemContainerMovieBinding.root) {
        private val itemContainerBinding: ItemContainerMovieBinding = itemContainerMovieBinding

        fun bindMovies(dataEntity: DataEntity) {
            itemContainerBinding.model = dataEntity
            itemContainerBinding.executePendingBindings()

            itemContainerBinding.root.setOnClickListener {
                itemListener.onItemClicked(dataEntity)
            }
        }
    }

}