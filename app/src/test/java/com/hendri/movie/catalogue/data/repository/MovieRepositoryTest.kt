package com.hendri.movie.catalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.data.source.local.MovieDataSource
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailGenreMovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieWithGenre
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieWithGenre
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.utils.Executors
import com.hendri.movie.catalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest{
    private lateinit var repository: MovieRepositoryFake

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var executors: Executors

    @Mock
    private lateinit var local: MovieDataSource

    @Mock
    private lateinit var remote: RemoteDataSource

    @Mock
    lateinit var dataSourceFactory: DataSource.Factory<Int, MovieWithGenre>

    @Before
    fun setUp() {
        repository = MovieRepositoryFake(executors, local, remote)
    }

    @Test
    fun getMovies() {
        val simpleSQLiteQuery = SimpleSQLiteQuery("")

        Mockito.`when`(local.getResultRawQuery(simpleSQLiteQuery)).thenReturn(dataSourceFactory)

        val value = LiveDataTestUtil.getValue(repository.getResult(simpleSQLiteQuery))

        verify(local).getResultRawQuery(simpleSQLiteQuery)
        assertNotNull(value)
    }

    @Test
    fun getDetailMovie() {
        val pk = 100
        val entity = DetailMovieResponseEntity(pk)
        val genre = listOf(DetailGenreMovieEntity(102, 19, 10, "test"))

        Mockito.`when`(local.getDetail(pk)).thenReturn(MutableLiveData(DetailMovieWithGenre(entity, genre)))

        val resource = LiveDataTestUtil.getValue(repository.getDetail(pk))
        verify(local).getDetail(pk)
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(pk, resource.data.id)
                assertEquals(genre[0].genre_code, resource.data.genres[0].genre_code)
                assertEquals(genre[0].name, resource.data.genres[0].name)
            } else -> return
        }
    }

    @Test
    fun getFavoriteMovies() {
        val simpleSQLiteQuery = SimpleSQLiteQuery("")

        Mockito.`when`(local.getFavorite(simpleSQLiteQuery)).thenReturn(dataSourceFactory)

        val value = LiveDataTestUtil.getValue(repository.getFavorite(simpleSQLiteQuery))

        verify(local).getFavorite(simpleSQLiteQuery)
        assertNotNull(value)
    }
}