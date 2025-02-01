package ir.rezazarchi.metamovie.database.fake

import ir.rezazarchi.metamovie.database.entity.BookmarkedMovieEntity

object FakeBookmarkedList {
    val bookmarkedList = mutableListOf(
        BookmarkedMovieEntity(1),
        BookmarkedMovieEntity(2),
        BookmarkedMovieEntity(4)
    )
}