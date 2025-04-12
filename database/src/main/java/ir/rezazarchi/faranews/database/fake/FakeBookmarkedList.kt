package ir.rezazarchi.faranews.database.fake

import ir.rezazarchi.faranews.database.entity.BookmarkedMovieEntity

object FakeBookmarkedList {
    val bookmarkedList = mutableListOf(
        BookmarkedMovieEntity(1),
        BookmarkedMovieEntity(2),
        BookmarkedMovieEntity(4)
    )
}