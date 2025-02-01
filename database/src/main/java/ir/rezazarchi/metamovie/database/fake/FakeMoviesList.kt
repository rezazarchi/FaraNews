package ir.rezazarchi.metamovie.database.fake

import ir.rezazarchi.metamovie.database.entity.MovieEntity
import ir.rezazarchi.metamovie.database.entity.VideoStats

object FakeMoviesList {
    val moviesList = listOf(
        MovieEntity(
            id = 1,
            tags = listOf("tag1", "tag2"),
            thumbnailUrl = "",
            videoUrl = "",
            username = "Reza Z",
            videoStats = VideoStats(0, 0, 0),
        ),
        MovieEntity(
            id = 2,
            tags = listOf("tag1", "tag2"),
            thumbnailUrl = "",
            videoUrl = "",
            username = "Ali Z",
            videoStats = VideoStats(0, 0, 0),
        ),
        MovieEntity(
            id = 3,
            tags = listOf("tag1", "tag2"),
            thumbnailUrl = "",
            videoUrl = "",
            username = "Reza Z",
            videoStats = VideoStats(0, 0, 0),
        ),
        MovieEntity(
            id = 4,
            tags = listOf("tag1", "tag2"),
            thumbnailUrl = "",
            videoUrl = "",
            username = "Reza Z",
            videoStats = VideoStats(0, 0, 0),
        ),
    )

}