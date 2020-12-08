package com.hendri.movie.catalogue.utils

import com.hendri.movie.catalogue.data.model.*

object DummyData {
    fun getMovie(): List<Movie> {
        return listOf(
            Movie(
                adult = false,
                backdrop_path = "/8e2j8EeJ1z2OCmbGmMaX6xg1u2w.jpg",
                genre_ids = listOf(35),
                id = 650747,
                original_language = "es",
                original_title = "Historias lamentables",
                overview = "This satirical anthology tells the surreal stories of a gift for Don Horacio, a trip to the beach for Bermejo, a life-changing relationship between Tina and the young immigrant Ayoub, and a new client for a company that specializes in excuses.",
                poster_path = "/sp4zXS3x4wHyL8wm8zLioiBrxuR.jpg",
                release_date = "2020-11-19",
                title = "Historias lamentables",
                video = false,
                vote_average = 7.1f,
                vote_count = 43
            ),
            Movie(
                adult = false,
                backdrop_path = "/ckfwfLkl0CkafTasoRw5FILhZAS.jpg",
                genre_ids = listOf(28, 35, 14),
                id = 602211,
                original_language = "en",
                original_title = "Fatman",
                overview = "A rowdy, unorthodox Santa Claus is fighting to save his declining business. Meanwhile, Billy, a neglected and precocious 12 year old, hires a hit man to kill Santa after receiving a lump of coal in his stocking.",
                popularity = 1610.823,
                poster_path = "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg",
                release_date = "2020-11-13",
                title = "Fatman",
                video = false,
                vote_average = 6.0f,
                vote_count = 99
            )
        )
    }

    fun getTvShow(): List<TvShow> {
        return listOf(
            TvShow(
                backdrop_path = "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
                first_air_date = "2017-09-25",
                genre_ids = listOf(18),
                id = 71712,
                name = "The Good Doctor",
                origin_country = listOf("US"),
                original_language = "en",
                original_name = "The Good Doctor",
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                popularity = 965.279,
                poster_path = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                vote_average = 8.6f,
                vote_count = 5920
            ),
            TvShow(
                backdrop_path = "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                first_air_date = "2005-03-27",
                genre_ids = listOf(18),
                id = 1416,
                name = "Grey's Anatomy",
                origin_country = listOf("US"),
                original_language = "en",
                original_name = "Grey's Anatomy",
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                popularity = 960.892,
                poster_path = "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                vote_average = 8.1f,
                vote_count = 4499
            )
        )
    }

    fun getDetailMovie(): DetailMovie {
        return DetailMovie(
            isFavorite = true,
            adult = false,
            backdrop_path = "/8e2j8EeJ1z2OCmbGmMaX6xg1u2w.jpg",
            budget = 0,
            genres = listOf(
                Genre(35, "Comedy")
            ),
            homepage = "https://www.amazon.com/dp/B08NWNX73Q",
            id = 650747,
            imdb_id = "tt11362866",
            original_language = "es",
            original_title = "Historias lamentables",
            overview = "This satirical anthology tells the surreal stories of a gift for Don Horacio, a trip to the beach for Bermejo, a life-changing relationship between Tina and the young immigrant Ayoub, and a new client for a company that specializes in excuses.",
            popularity = 1044.557,
            poster_path = "/sp4zXS3x4wHyL8wm8zLioiBrxuR.jpg",
            release_date = "2020-11-19",
            revenue = 0,
            runtime = 129,
            status = "Released",
            tagline = "",
            title = "Historias lamentables",
            video = false,
            vote_average = 6.9f,
            vote_count = 45
        )
    }

    fun getDetailTvShow(): DetailTvShow {
        return DetailTvShow(
            isFavorite = true,
            backdrop_path = "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
            episode_run_time = listOf(42),
            first_air_date = "2017-09-25",
            genres = listOf(
                Genre(18, "Drama")
            ),
            homepage = "http://abc.go.com/shows/the-good-doctor",
            id = 71712,
            in_production = true,
            languages = listOf("en"),
            last_air_date = "2020-11-30",
            name = "The Good Doctor",
            number_of_episodes = 66,
            number_of_seasons = 4,
            origin_country = listOf("US"),
            original_language = "en",
            original_name = "The Good Doctor",
            overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
            popularity = 965.279,
            poster_path = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            status = "Returning Series",
            tagline = "His mind is a mystery, his methods are a miracle.",
            type = "Scripted",
            vote_average = 8.6f,
            vote_count = 5928
        )
    }
}