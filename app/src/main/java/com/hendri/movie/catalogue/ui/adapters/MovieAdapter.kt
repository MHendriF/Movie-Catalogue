package com.hendri.movie.catalogue.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.databinding.ItemContainerMoviesBinding
import com.hendri.movie.catalogue.ui.listeners.MovieListener

class MovieAdapter(private val itemListener: MovieListener): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var movies: List<Movie>
    private lateinit var layoutInflater: LayoutInflater

    fun setData(movies: List<Movie>) {
        this.movies = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding: ItemContainerMoviesBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_container_movies, parent, false
        )
        return MovieViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovies(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemContainerMovieBinding: ItemContainerMoviesBinding) :
        RecyclerView.ViewHolder(itemContainerMovieBinding.root) {
        private val itemContainerBinding: ItemContainerMoviesBinding = itemContainerMovieBinding

        fun bindMovies(movie: Movie) {
            itemContainerBinding.model = movie
            itemContainerBinding.executePendingBindings()

            itemContainerBinding.root.setOnClickListener {
                itemListener.onItemClicked(movie)
            }
        }
    }

}