package com.hendri.movie.catalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.hendri.movie.catalogue.data.Resource
import com.hendri.movie.catalogue.data.source.local.MovieDataSource
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailGenreMovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieRelation
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieGenreRelation
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.utils.Executors
import com.hendri.movie.catalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
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
    lateinit var dataSourceFactory: DataSource.Factory<Int, MovieGenreRelation>

    @Before
    fun setUp() {
        repository = MovieRepositoryFake(executors, local, remote)
    }

    @Test
    fun get_result() {
        val simpleSQLiteQuery = SimpleSQLiteQuery("")

        Mockito.`when`(local.getResultRawQuery(simpleSQLiteQuery)).thenReturn(dataSourceFactory)

        val value = LiveDataTestUtil.getValue(repository.getResult(simpleSQLiteQuery))

        verify(local).getResultRawQuery(simpleSQLiteQuery)
        assertNotNull(value)
    }

    @Test
    fun get_detail() {
        val pk = 100
        val entity = DetailMovieResponseEntity(pk)
        val genre = listOf(DetailGenreMovieEntity(102, 19, 10, "test"))

        Mockito.`when`(local.getDetail(pk)).thenReturn(MutableLiveData(DetailMovieRelation(entity, genre)))

        val resource = LiveDataTestUtil.getValue(repository.getDetail(pk))
        verify(local).getDetail(pk)
        Assert.assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                Assert.assertEquals(pk, resource.data.id)
                Assert.assertEquals(genre[0].genre_code, resource.data.genres[0].genre_code)
                Assert.assertEquals(genre[0].name, resource.data.genres[0].name)
            }
        }
    }

    @Test
    fun get_favorite() {
        val simpleSQLiteQuery = SimpleSQLiteQuery("")

        Mockito.`when`(local.getFavorite(simpleSQLiteQuery)).thenReturn(dataSourceFactory)

        val value = LiveDataTestUtil.getValue(repository.getFavorite(simpleSQLiteQuery))

        verify(local).getFavorite(simpleSQLiteQuery)
        assertNotNull(value)
    }
}