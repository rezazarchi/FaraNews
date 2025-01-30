package ir.rezazarchi.metamovie.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationRoute {

    @Serializable
    data object MovieList : NavigationRoute

    @Serializable
    data class MovieDetails(val id: Long) : NavigationRoute
}