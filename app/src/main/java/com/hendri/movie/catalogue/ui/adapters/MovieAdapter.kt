package com.hendri.movie.catalogue.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.databinding.ItemContainerMovieBinding
import com.hendri.movie.catalogue.ui.listeners.ItemListener

class MovieAdapter(private val itemListener: ItemListener): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var movies: List<DataEntity>
    private lateinit var layoutInflater: LayoutInflater
    //private val itemListener: ItemListener? = null

    fun addMovies(movies: List<DataEntity>) {
        this.movies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val movieBinding: ItemContainerMovieBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_container_movie, parent, false
        )
        return MovieViewHolder(movieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovies(movies[position])
        //holder.itemView.setOnClickListener { Log.d("tes", position.toString()) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemContainerMovieBinding: ItemContainerMovieBinding) :
        RecyclerView.ViewHolder(itemContainerMovieBinding.root) {
        private val itemContainerBinding: ItemContainerMovieBinding = itemContainerMovieBinding

        fun bindMovies(movie: DataEntity) {
            itemContainerBinding.movie = movie
            itemContainerBinding.executePendingBindings()

            itemContainerBinding.root.setOnClickListener {
                itemListener.onItemClicked(movie)
            }
        }
    }

    private fun clickItem(movie: DataEntity): Unit? {
        return itemListener?.onItemClicked(movie)
    }

}