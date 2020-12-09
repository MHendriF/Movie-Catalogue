package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.data.model.DetailMovie
import com.hendri.movie.catalogue.data.model.DetailTvShow
import com.hendri.movie.catalogue.data.repository.MovieRepository
import com.hendri.movie.catalogue.data.repository.TvShowRepository
import com.hendri.movie.catalogue.ui.activities.DetailActivity.Companion.DATA_DESTINATION
import com.hendri.movie.catalogue.ui.activities.DetailActivity.Companion.DATA_ID
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    private val tvShowRepo: TvShowRepository
) : ViewModel() {

    private lateinit var dataExtra: MutableList<Int>

    val movie by lazy { MediatorLiveData<Resource<DetailMovie>>() }
    val tvShow by lazy { MediatorLiveData<Resource<DetailTvShow>>() }

    fun init(dataDes: Int, dataId: Int) {
        dataExtra = mutableListOf(dataDes, dataId)
        when (dataExtra[DATA_DESTINATION]) {
            R.id.detail_movie -> movie.addSource(movieRepo.getDetail(dataExtra[DATA_ID])) {
                movie.value = it
            }
            R.id.detail_tv_show -> tvShow.addSource(tvShowRepo.getDetail(dataExtra[DATA_ID])) {
                tvShow.value = it
            }
        }
    }

    fun getExtra(data: Int) = this.dataExtra[data]

    fun setFavoriteMovie(id: Int, isFavorite: Boolean) = movieRepo.setFavorite(id, isFavorite)

    fun setFavoriteTv(id: Int, isFavorite: Boolean) = tvShowRepo.setFavorite(id, isFavorite)
}