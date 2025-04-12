package ir.rezazarchi.faranews.features.details.presentation.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.faranews.core.data.Result
import ir.rezazarchi.faranews.core.data.onError
import ir.rezazarchi.faranews.core.data.onSuccess
import ir.rezazarchi.faranews.features.details.domain.usecase.MovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsDetailsViewModel(
    val movieDetails: MovieDetailsUseCase,
    val isBookmarked: ir.rezazarchi.faranews.bookmark.domain.usecase.IsBookmarkedUseCase,
    val toggleBookmarkUseCase: ir.rezazarchi.faranews.bookmark.domain.usecase.ToggleBookmarkUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NewsDetailsState())
    val state = _state.asStateFlow()

    fun onEvent(event: NewsDetailsEvents) {
        when (event) {
            is NewsDetailsEvents.GetNewsDetails -> {
                getMovieDetailsWithBookmarkStatus(event.newsId)
            }

            is NewsDetailsEvents.ToggleBookmark -> {
                toggleBookmark(event.newsId, event.bookmarked)
            }
        }
    }

    private fun getMovieDetailsWithBookmarkStatus(movieId: Long) {
        combine(movieDetails(movieId), isBookmarked(movieId)) { movieDetails, isBookmarked ->
            movieDetails
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            newsDetails = result,
                            isBookmarked = isBookmarked is Result.Success && isBookmarked.data,
                            isLoading = false,
                            error = null,
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            newsDetails = null,
                            isBookmarked = false,
                            isLoading = false,
                            error = it.error,
                        )
                    }
                }
        }.launchIn(viewModelScope)
    }

    private fun toggleBookmark(id: Long, bookmarked: Boolean) {
        viewModelScope.launch {
            toggleBookmarkUseCase(id, bookmarked)
        }
    }

}