package com.hendri.movie.catalogue.utils

import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity

object DataDummy {
    fun generateDummyMovies(): List<DataEntity> {
        val movies = ArrayList<DataEntity>()

        movies.add(
            DataEntity(
                "MOVIE_1",
                "A Star Is Born",
                "Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
                "Drama, Percintaan, Musik",
                "2018",
                R.drawable.poster_a_star_is_born,
                R.drawable.latar_a_star)
        )
        movies.add(DataEntity(
            "MOVIE_2",
            "Alita: Battle Angle",
            "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.",
            "Aksi, Cerita Fiksi, Petualangan",
            "2019",
            R.drawable.poster_alita,
            R.drawable.latar_alita))
        movies.add(DataEntity(
            "MOVIE_3",
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "Drama, Musik",
            "2018",
            R.drawable.poster_bohemian,
            R.drawable.latar_bohemian))
        movies.add(DataEntity(
            "MOVIE_4",
            "Cold Pursuit",
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "Aksi, Kejahatan, Cerita Seru",
            "2019",
            R.drawable.poster_cold_persuit,
            R.drawable.latar_cold_persuit))
        movies.add(DataEntity(
            "MOVIE_5",
            "How to Train Your Dragon: The Hidden World",
            "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
            "Animasi, Keluarga, Petualangan",
            "2019",
            R.drawable.poster_how_to_train,
            R.drawable.latar_how_to_train))
        movies.add(DataEntity(
            "MOVIE_6",
            "Avengers: Infinity War",
            "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi.",
            "Petualangan, Aksi, Cerita Seru",
            "2018",
            R.drawable.poster_infinity_war,
            R.drawable.latar_infinity_war))
        movies.add(DataEntity(
            "MOVIE_7",
            "Ralph Breaks the Internet",
            "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            "Keluarga, Animasi, Komedi, Petualangan",
            "2018",
            R.drawable.poster_ralph,
            R.drawable.latar_ralph))
        movies.add(DataEntity(
            "MOVIE_8",
            "Robin Hood",
            "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            "Petualangan, Aksi, Cerita Seru",
            "2018",
            R.drawable.poster_robin_hood,
            R.drawable.latar_robin_hood))
        movies.add(DataEntity(
            "MOVIE_9",
            "Spider-Main: Into the Spider-Verse",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "Aksi, Petualangan, Animasi",
            "2018",
            R.drawable.poster_spiderman,
            R.drawable.latar_spiderman))
        movies.add(DataEntity(
            "MOVIE_10",
            "T-34",
            "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
            "Kejahatan, Aksi, Drama, Sejarah",
            "2018",
            R.drawable.poster_t34,
            R.drawable.latar_t34))
        return movies
    }

    fun generateDummyTvShows(): List<DataEntity> {
        val tvShows = ArrayList<DataEntity>()

        tvShows.add(DataEntity(
            "TVSHOW_1",
            "The Arrow",
            "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
            "Kejahatan, Drama, Misteri",
            "2012",
            R.drawable.poster_arrow,
            R.drawable.latar_arrow))
        tvShows.add(DataEntity(
            "TVSHOW_2",
            "Doom Patrol",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "Sci-fi & Fantasy, Aksi & Petualangan",
            "2019",
            R.drawable.poster_doom_patrol,
            R.drawable.latar_doom_patrol_))
        tvShows.add(
            DataEntity(
            "TVSHOW_3",
            "Family Guy",
            "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ).",
            "Animasi, Komedi",
            "1999",
            R.drawable.poster_family_guy,
            R.drawable.latar_family_guy)
        )
        tvShows.add(DataEntity(
            "TVSHOW_4",
            "The Flash",
            "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
            "Drama, Sci-fi & Fantasy",
            "2014",
            R.drawable.poster_flash,
            R.drawable.latar_flash))
        tvShows.add(DataEntity(
            "TVSHOW_5",
            "Gotham",
            "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka.",
            "Drama, Fantasi, Kejahatan",
            "2014",
            R.drawable.poster_gotham,
            R.drawable.latar_gotham))
        tvShows.add(DataEntity(
            "TVSHOW_6",
            "Grey's Anatomy",
            "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
            "Drama",
            "2005",
            R.drawable.poster_grey_anatomy,
            R.drawable.latar_grey_anatomy))
        tvShows.add(DataEntity(
            "TVSHOW_7",
            "Hanna",
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            "Aksi & Petualangan, Drama",
            "2019",
            R.drawable.poster_hanna,
            R.drawable.latar_hanna))
        tvShows.add(DataEntity(
            "TVSHOW_8",
            "Naruto Shippuden",
            "Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki.",
            "Animasi, Komedi, Drama",
            "2007",
            R.drawable.poster_naruto_shipudden,
            R.drawable.latar_naruto_shipudden))
        tvShows.add(DataEntity(
            "TVSHOW_9",
            "NCIS",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            "Aksi & Petualangan, Kejahatan, Drama",
            "2003",
            R.drawable.poster_ncis,
            R.drawable.latar_ncis))
        tvShows.add(DataEntity(
            "TVSHOW_10",
            "The Simpsons",
            "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
            "Animasi, Komedi, Keluarga, Drama",
            "1989",
            R.drawable.poster_the_simpson,
            R.drawable.latar_the_simpson))

        return tvShows
    }
}