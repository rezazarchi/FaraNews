package ir.rezazarchi.metamovie.features.search.data.remote.fake

import ir.rezazarchi.metamovie.features.search.data.remote.dto.Hit
import ir.rezazarchi.metamovie.features.search.data.remote.dto.SearchMovieDto
import ir.rezazarchi.metamovie.features.search.data.remote.dto.Video
import ir.rezazarchi.metamovie.features.search.data.remote.dto.Videos

object FakeSearchMovieDto {
    val fakeSearchDto = SearchMovieDto(
        totalHits = 2,
        total = 2,
        hits = listOf(
            Hit(
                id = 1,
                tags = "movie1",
                user = "RezaZ",
                pageURL = null,
                type = "film",
                duration = 18,
                videos = Videos(null, null, null, Video("", 1, 2, 1, "")),
                views = 1,
                downloads = 1,
                likes = 1,
                comments = 1,
                userId = 156,
                userImageURL = null,
            ),
            Hit(
                id = 1,
                tags = "movie1",
                user = "AliZ",
                pageURL = null,
                type = "film",
                duration = 18,
                videos = Videos(null, null, null, Video("", 1, 2, 1, "")),
                views = 1,
                downloads = 1,
                likes = 1,
                comments = 1,
                userId = 123,
                userImageURL = null,
            )
        )
    )
}