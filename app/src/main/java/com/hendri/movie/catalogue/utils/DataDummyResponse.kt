package com.hendri.movie.catalogue.utils

import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.MovieResponse
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.data.response.TvShowResponse

object DataDummyResponse {
    fun movieResponse() : MovieResponse {
        val list: List<Movie> = listOf(
            Movie(
                uid = 1,
                overview = "A professional thief with $40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.",
                originalLanguage = "en",
                originalTitle = "Money Plane",
                title = "Money Plane",
                posterPath = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
                backdropPath = "/pq0JSpwyT2URytdFG0euztQPAyR.jpg",
                releaseDate = "2020-09-29",
                popularity = 2151.476,
                voteAverage = 5.5,
                id = 694919,
                voteCount = 22,
            ),
            Movie(
                uid = 2,
                overview = "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it." ,
                originalLanguage = "en",
                originalTitle = "Hard Kill",
                title = "Hard Kill",
                posterPath = "/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
                backdropPath = "/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
                releaseDate = "2020-08-25",
                popularity = 1218.495,
                voteAverage = 5.1,
                id = 724989,
                voteCount = 86,
            ),
        )
        return MovieResponse(1, (list.size-1), list, 1)
    }

    fun tvShowResponse(): TvShowResponse {
        val list: List<TvShow> = listOf(
            TvShow(
                uid = 1,
                overview = "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                originalLanguage = "en",
                originalName = "The Boys",
                name = "The Boys",
                posterPath = "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                backdropPath = "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
                firstAirDate = "2020-09-29",
                popularity =  2135.961,
                voteAverage = 8.4,
                id = 76479,
                voteCount = 2204,
            ),
            TvShow(
                uid = 2,
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                originalLanguage = "en",
                originalName = "Lucifer",
                name = "Lucifer",
                posterPath = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                backdropPath = "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                firstAirDate = "2016-01-25",
                popularity = 1067.008,
                voteAverage = 8.5,
                id = 63174,
                voteCount = 5340,
            ),
        )
        return TvShowResponse(1, (list.size-1), list, 1)
    }
}