package com.hendri.movie.catalogue.utils

import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.the_movie_db.*

object DummyDataResponse {
    fun movieResponse(): MovieResponse {
        val list: List<Movie> = listOf(
            Movie(
                popularity = 2151.476,
                vote_count = 22,
                poster_path = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
                id = 694919,
                backdrop_path = "/pq0JSpwyT2URytdFG0euztQPAyR.jpg",
                original_language = "en",
                original_title = "Money Plane",
                genre_ids = listOf(28),
                title = "Money Plane",
                vote_average = 5.5f,
                release_date = "2020-09-29",
                overview = "A professional thief with $40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals."
            ),
            Movie(
                popularity = 1218.495,
                vote_count = 86,
                poster_path = "/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
                id = 724989,
                backdrop_path = "/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
                original_language = "en",
                original_title = "Hard Kill",
                genre_ids = listOf(28, 53),
                title = "Hard Kill",
                vote_average = 5.1f,
                release_date = "2020-08-25",
                overview = "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it."
            ),
        )
        return MovieResponse(1, (list.size - 1), 1, list)
    }

    fun tvShowResponse(): TvShowResponse {
        val list: List<TvShow> = listOf(
            TvShow(
                original_name = "The Boys",
                genre_ids = listOf(10759, 10765),
                name = "The Boys",
                popularity = 2135.961,
                origin_country = listOf("US"),
                vote_count = 2204,
                first_air_date = "2019-07-25",
                backdrop_path = "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
                original_language = "en",
                id = 76479,
                vote_average = 8.4f,
                overview = "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                poster_path = "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg"
            ),
            TvShow(
                original_name = "Lucifer",
                genre_ids = listOf(80, 10765),
                name = "Lucifer",
                popularity = 1067.008,
                origin_country = listOf("US"),
                vote_count = 5340,
                first_air_date = "2016-01-25",
                backdrop_path = "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                original_language = "en",
                id = 63174,
                vote_average = 8.5f,
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                poster_path = "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
            ),
        )
        return TvShowResponse(1, (list.size - 1), 1, list)
    }

    fun detailMovieResponse(): DetailMovieResponse {
        return DetailMovieResponse(
            backdrop_path = "/pq0JSpwyT2URytdFG0euztQPAyR.jpg",
            genres = listOf(Genres(28, "Action")),
            id = 694919,
            imDbId = "tt7286966",
            original_language = "en",
            original_title = "Money Plane",
            overview = "A professional thief with \$40 million in debt and his family's life on the line must commit one final heist - rob a futuristic airborne casino filled with the world's most dangerous criminals.",
            popularity = 2151.476,
            poster_path = "/6CoRTJTmijhBLJTUNoVSUNxZMEI.jpg",
            production_companies = listOf(
                ProductionCompanies(
                    92109, "/1wOgBio7WGOskOAQ2aTvtCHAdm8.png", "Dawn's Light", "US"
                ),
                ProductionCompanies(
                    87067, "/q9yy7dYaJX80lqEwpGb2rCdXwg0.png", "Taylor & Dodge", "US"
                ),
            ),
            production_countries = listOf(ProductionCountries("US", "United States of America")),
            release_date = "2020-09-29",
            runtime = 82,

            spoken_languages = listOf(SpokenLanguages("en", "English")),
            status = "Released",
            title = "Money Plane",
            vote_average = 5.5f,
            vote_count = 22
        )
    }

    fun detailTvShowResponse(): DetailTvShowResponse {
        return DetailTvShowResponse(
            backdrop_path = "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
            created_by = listOf(
                Created(
                    58321,
                    "5f3814c011c066003637f6a8",
                    "Eric Kripke",
                    2,
                    "/dd7EgfOEKPqQxWtBfAvjYZahbSc.jpg"
                )
            ),

            episode_run_time = listOf(60),
            first_air_date = "2019-07-25",
            genres = listOf(
                Genres(10765, "Sci-Fi & Fantasy"),
                Genres(10759, "Action & Adventure")
            ),
            homepage = "https://www.amazon.com/dp/B0875L45GK",
            id = 76479,
            in_production = true,
            languages = listOf("en"),
            last_air_date = "2020-09-25",
            last_episode_to_air = LastEpisodeToAir(
                id = 2324565,
                name = "The Bloody Doors Off",
                episode_number = 6,
                air_date = "2020-09-25",
                overview = "The Sage Grove Center® is dedicated to caring for those struggling with mental illness. Our compassionate doctors and counselors provide personalized services to help patients live their best lives. If you or a loved one need help, call the Sage Grove Center today at 1-800-122-8585. A proud subsidiary of Global Wellness Services®, which is a proud subsidiary of Vought International®",
                production_code = "THBY 206",
                season_number = 2,
                show_id = 76479,
                still_path = "/p4U250LqNLjHYe6kY4dxbsmN2t2.jpg",
            ),
            name = "The Boys",
            next_episode_to_air = NextEpisodeToAir(
                air_date = "2020-10-02",
                episode_number = 7,
                id = 2324563,
                name = "Butcher, Baker, Candlestick Maker",
                season_number = 2,
                show_id = 76479,
            ),

            networks = listOf(
                Networks(
                    name = "Amazon",
                    id = 1024,
                    logo_path = "/ifhbNuuVnlwYy5oXA5VIb2YR8AZ.png",
                    origin_country = "US"
                )
            ),
            number_of_episodes = 16,
            number_of_seasons = 2,
            origin_country = listOf("US"),
            original_language = "en",
            original_name = "The Boys",
            overview = "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
            popularity = 2135.961,
            poster_path = "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            production_companies = listOf(
                ProductionCompanies(
                    id = 20580,
                    logo_path = "/tkFE81jJIqiFYPP8Tho57MXRQEx.png",
                    name = "Amazon Studios",
                    origin_country = "US"
                ),
                ProductionCompanies(
                    id = 333,
                    logo_path = "/5xUJfzPZ8jWJUDzYtIeuPO4qPIa.png",
                    name = "Original Film",
                    origin_country = "US"
                ),
                ProductionCompanies(
                    id = 38398,
                    logo_path = "",
                    "Kripke Enterprises",
                    origin_country = "US"
                )
            ),
            seasons = listOf(
                Seasons(
                    air_date = "2020-09-09",
                    episode_count = 1,
                    id = 163277,
                    name = "Specials",
                    overview = "",
                    poster_path = "/cCXG81dSCPXcqYm6gTHlwtXocti.jpg",
                    season_number = 0
                ),
                Seasons(
                    air_date = "2020-09-04",
                    episode_count = 8,
                    id = 154681,
                    name = "Season 2",
                    overview = "The even more intense, more insane season two finds The Boys on the run from the law, hunted by the Supes, and desperately trying to regroup and fight back against Vought. In hiding, Hughie, Mother’s Milk, Frenchie and Kimiko try to adjust to a new normal, with Butcher nowhere to be found. Meanwhile, Starlight must navigate her place in The Seven as Homelander sets his sights on taking complete control. His power is threatened with the addition of Stormfront, a social media-savvy new Supe, who has an agenda of her own. On top of that, the Supervillain threat takes center stage and makes waves as Vought seeks to capitalize on the nation’s paranoia.",
                    poster_path = "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                    season_number = 2
                )
            ),
            status = "Returning Series",
            type = "Scripted",
            vote_average = 8.4f,
            vote_count = 2220
        )
    }
}