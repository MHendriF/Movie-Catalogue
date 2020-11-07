package com.hendri.movie.catalogue.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.databinding.FragmentMovieBinding
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.ui.listeners.ItemListener
import com.hendri.movie.catalogue.ui.repositories.MovieRepository
import com.hendri.movie.catalogue.ui.viewmodels.MovieViewModel
import timber.log.Timber


class MovieFragment : Fragment(), ItemListener {

    private lateinit var movieBinding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movies : List<DataEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        movieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return movieBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        doInitialization()
    }

    private fun doInitialization() {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movies = viewModel.getMovies()
        movieAdapter = MovieAdapter(this)
        movieAdapter.addMovies(movies)
        movieBinding.rvMovie.setHasFixedSize(true)
        movieBinding.rvMovie.adapter = movieAdapter
    }

    override fun onItemClicked(dataEntity: DataEntity) {
        Timber.d("Trace :: data(${dataEntity.title})")
    }

}