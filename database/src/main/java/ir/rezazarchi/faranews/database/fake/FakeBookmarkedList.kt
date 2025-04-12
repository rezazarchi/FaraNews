package ir.rezazarchi.faranews.database.fake

import ir.rezazarchi.faranews.database.entity.BookmarkedNewsEntity

object FakeBookmarkedList {
    val bookmarkedList = mutableListOf(
        BookmarkedNewsEntity(1),
        BookmarkedNewsEntity(2),
        BookmarkedNewsEntity(4)
    )
}