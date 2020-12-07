package com.hendri.movie.catalogue.utils

import com.hendri.movie.catalogue.data.model.*
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieRelation
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailTvShowRelation
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailTvShowResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieGenreRelation
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowGenreRelation
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowResponseEntity
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse

object DataMapper {

    fun listMovieWithGenre(list: MutableList<MovieGenreRelation>?): List<Movie> {
        val listResult = mutableListOf<Movie>()
        list?.forEach {
            listResult.add(Movie(
                vote_average = it.movieEntity.vote_average,
                video = it.movieEntity.video,
                popularity = it.movieEntity.popularity,
                vote_count = it.movieEntity.vote_count,
                poster_path = it.movieEntity.poster_path,
                original_title = it.movieEntity.original_title,
                overview = it.movieEntity.overview,
                release_date = it.movieEntity.release_date,
                title = it.movieEntity.title,
                original_language = it.movieEntity.original_language,
                backdrop_path = it.movieEntity.backdrop_path,
                id = it.movieEntity.pk,
                adult = it.movieEntity.adult,
                genre_ids = it.genreIds.map { genreIds -> genreIds.genre }
            ))
        }
        return listResult
    }

    fun listTvShowWithGenre(list: MutableList<TvShowGenreRelation>?): List<TvShow> {
        val listResult = mutableListOf<TvShow>()
        list?.forEach {
            listResult.add(
                TvShow(
                    vote_average = it.tvShowEntity.vote_average,
                    popularity = it.tvShowEntity.popularity,
                    vote_count = it.tvShowEntity.vote_count,
                    poster_path = it.tvShowEntity.poster_path,
                    overview = it.tvShowEntity.overview,
                    original_language = it.tvShowEntity.original_language,
                    backdrop_path = it.tvShowEntity.backdrop_path,
                    genre_ids = it.genreIds.map { genreIds -> genreIds.genre },
                    name = it.tvShowEntity.name,
                    first_air_date = it.tvShowEntity.first_air_date,
                    original_name = it.tvShowEntity.original_name,
                    id = it.tvShowEntity.id_tv_result,
                )
            )
        }
        return listResult
    }

    fun tvResultToTvShow(TvShowGenreRelation: TvShowGenreRelation): TvShow {
        return TvShow(
            vote_average = TvShowGenreRelation.tvShowEntity.vote_average,
            popularity = TvShowGenreRelation.tvShowEntity.popularity,
            vote_count = TvShowGenreRelation.tvShowEntity.vote_count,
            poster_path = TvShowGenreRelation.tvShowEntity.poster_path,
            overview = TvShowGenreRelation.tvShowEntity.overview,
            original_language = TvShowGenreRelation.tvShowEntity.original_language,
            backdrop_path = TvShowGenreRelation.tvShowEntity.backdrop_path,
            genre_ids = TvShowGenreRelation.genreIds.map { genreIds -> genreIds.genre },
            name = TvShowGenreRelation.tvShowEntity.name,
            first_air_date = TvShowGenreRelation.tvShowEntity.first_air_date,
            original_name = TvShowGenreRelation.tvShowEntity.original_name,
            id = TvShowGenreRelation.tvShowEntity.id_tv_result
        )
    }

    fun movieDetailToMovieDetailModel(movieDetail: DetailMovieRelation) =
        movieDetail?.run {
            DetailMovie(
                isFavorite = movieDetailResponseEntity.isFavorite,
                id = movieDetailResponseEntity.pk,
                backdrop_path = movieDetailResponseEntity.backdrop_path,
                adult = movieDetailResponseEntity.adult,
                original_language = movieDetailResponseEntity.original_language,
                title = movieDetailResponseEntity.title,
                release_date = movieDetailResponseEntity.release_date,
                overview = movieDetailResponseEntity.overview,
                original_title = movieDetailResponseEntity.original_title,
                poster_path = movieDetailResponseEntity.poster_path,
                vote_count = movieDetailResponseEntity.vote_count,
                popularity = movieDetailResponseEntity.popularity,
                video = movieDetailResponseEntity.video,
                vote_average = movieDetailResponseEntity.vote_average,
                tagline = movieDetailResponseEntity.tagline,
                status = movieDetailResponseEntity.status,
                runtime = movieDetailResponseEntity.runtime,
                revenue = movieDetailResponseEntity.revenue,
                imdb_id = movieDetailResponseEntity.imdb_id,
                homepage = movieDetailResponseEntity.homepage,
                budget = movieDetailResponseEntity.budget,
                genres = genre.map { Genre(it.genre_code, it.name) }
            )
        }

    fun tvDetailToTvDetailModel(tvDetail: DetailTvShowRelation?) =
        tvDetail?.run {
            DetailTvShow(
                isFavorite = tvDetailResponseEntity.isFavorite,
                id = tvDetailResponseEntity.pk,
                backdrop_path = tvDetailResponseEntity.backdrop_path,
                original_language = tvDetailResponseEntity.original_language,
                overview = tvDetailResponseEntity.overview,
                poster_path = tvDetailResponseEntity.poster_path,
                vote_count = tvDetailResponseEntity.vote_count,
                popularity = tvDetailResponseEntity.popularity,
                vote_average = tvDetailResponseEntity.vote_average,
                status = tvDetailResponseEntity.status,
                homepage = tvDetailResponseEntity.homepage,
                genres = genre.map { Genre(it.genre_code, it.name) },
                name = tvDetailResponseEntity.name,
                first_air_date = tvDetailResponseEntity.first_air_date,
                original_name = tvDetailResponseEntity.original_name,
                in_production = tvDetailResponseEntity.in_production,
                last_air_date = tvDetailResponseEntity.last_air_date,
                number_of_episodes = tvDetailResponseEntity.number_of_episodes,
                number_of_seasons = tvDetailResponseEntity.number_of_seasons,
                type = tvDetailResponseEntity.type
            )
        }

    fun movieResponseToMovieResponseEntity(movieResponse: MovieResponse): MovieResponseEntity {
        return MovieResponseEntity(
            page = movieResponse.page,
            total_results = movieResponse.total_results,
            total_pages = movieResponse.total_pages
        )
    }

    fun movieResultToMovieEntity(
        idInsertResponse: Long,
        movieResult: Movie
    ): MovieEntity {
        return MovieEntity(
            fk = idInsertResponse,
            pk = movieResult.id,
            popularity = movieResult.popularity,
            vote_count = movieResult.vote_count,
            poster_path = movieResult.poster_path,
            backdrop_path = movieResult.backdrop_path,
            original_language = movieResult.original_language,
            original_title = movieResult.original_title,
            title = movieResult.title,
            vote_average = movieResult.vote_average,
            overview = movieResult.overview,
            release_date = movieResult.release_date
        )
    }

    fun movieDetailResponseToMovieDetailResponseEntity(movieDetailResponse: DetailMovieResponse): DetailMovieResponseEntity {
        return DetailMovieResponseEntity(
            pk = movieDetailResponse.id,
            adult = movieDetailResponse.adult,
            backdrop_path = movieDetailResponse.backdrop_path,
            budget = movieDetailResponse.budget,
            homepage = movieDetailResponse.homepage,
            imdb_id = movieDetailResponse.imdb_id,
            original_language = movieDetailResponse.original_language,
            title = movieDetailResponse.title,
            release_date = movieDetailResponse.release_date,
            overview = movieDetailResponse.overview,
            vote_average = movieDetailResponse.vote_average,
            original_title = movieDetailResponse.original_title,
            poster_path = movieDetailResponse.poster_path,
            vote_count = movieDetailResponse.vote_count,
            popularity = movieDetailResponse.popularity,
            revenue = movieDetailResponse.revenue,
            runtime = movieDetailResponse.runtime,
            status = movieDetailResponse.status,
            tagline = movieDetailResponse.tagline,
            video = movieDetailResponse.video
        )
    }

    fun tvResponseToTvShowResponseEntity(tvResponse: TvShowResponse): TvShowResponseEntity {
        return TvShowResponseEntity(
            page = tvResponse.page,
            total_results = tvResponse.total_results,
            total_pages = tvResponse.total_pages
        )
    }

    fun tvResultToTvShowEntity(
        idInsertResponse: Long,
        TvShow: TvShow
    ): TvShowEntity {
        return TvShowEntity(
            id_tv_result_foreign = idInsertResponse,
            id_tv_result = TvShow.id,
            popularity = TvShow.popularity,
            vote_count = TvShow.vote_count,
            poster_path = TvShow.poster_path,
            backdrop_path = TvShow.backdrop_path,
            original_language = TvShow.original_language,
            vote_average = TvShow.vote_average,
            overview = TvShow.overview,
            original_name = TvShow.name,
            first_air_date = TvShow.first_air_date,
            name = TvShow.name
        )
    }

    fun tvDetailResponseToTvDetailEntity(tvDetailResponse: DetailTvShowResponse): DetailTvShowResponseEntity {
        return DetailTvShowResponseEntity(
            pk = tvDetailResponse.id,
            backdrop_path = tvDetailResponse.backdrop_path,
            homepage = tvDetailResponse.homepage,
            original_language = tvDetailResponse.original_language,
            overview = tvDetailResponse.overview,
            vote_average = tvDetailResponse.vote_average,
            poster_path = tvDetailResponse.poster_path,
            vote_count = tvDetailResponse.vote_count,
            popularity = tvDetailResponse.popularity,
            status = tvDetailResponse.status,
            type = tvDetailResponse.type,
            number_of_seasons = tvDetailResponse.number_of_seasons,
            number_of_episodes = tvDetailResponse.number_of_episodes,
            last_air_date = tvDetailResponse.last_air_date,
            in_production = tvDetailResponse.in_production,
            original_name = tvDetailResponse.original_name,
            first_air_date = tvDetailResponse.first_air_date,
            name = tvDetailResponse.name,
        )
    }
}