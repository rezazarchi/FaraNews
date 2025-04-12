package ir.rezazarchi.faranews.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationRoute {

    @Serializable
    data object NewsList : NavigationRoute

    @Serializable
    data class NewsDetails(val id: Long) : NavigationRoute
}