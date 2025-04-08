package ir.rezazarchi.metamovie.core.utils

import kotlinx.collections.immutable.persistentSetOf

object Constant {
    const val API_BASE_URL_VALUE = "https://newsapi.org/v2/"
    const val API_KEY = "89a3de9743a54ec490f290a0828b3b3b"
    val SEARCH_QUERIES = persistentSetOf("Microsoft", "Apple", "Google", "Tesla")
}