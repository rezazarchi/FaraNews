package ir.rezazarchi.faranews.features.bookmark.presentation.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.faranews.core.data.NetworkError
import ir.rezazarchi.faranews.core.data.onError
import ir.rezazarchi.faranews.core.data.onSuccess
import ir.rezazarchi.faranews.core.utils.toUiText
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedMovieDetailedUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.ToggleBookmarkUseCase
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedContracts.BookmarkedEvents
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedContracts.BookmarkedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarkedViewModel(
    private val bookmarkedMovies: BookmarkedMovieDetailedUseCase,
    private val removeBookmark: ToggleBookmarkUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkedState())
    val state = _state.onStart {
        fetchAllBookmarkedMoviesDetailed()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value,
    )

    fun onEvent(event: BookmarkedEvents) {
        when (event) {
            is BookmarkedEvents.RemoveBookmark -> {
                removeBookmarkFromDb(event.id)
            }
        }
    }

    private fun removeBookmarkFromDb(eventId: Long) {
        viewModelScope.launch {
            removeBookmark(eventId, false)
        }
    }

    private fun fetchAllBookmarkedMoviesDetailed() {
        viewModelScope.launch {
            bookmarkedMovies().collect {
                it.onSuccess { result ->
                    _state.update { it.copy(movies = result) }
                }.onError { error ->
                    _state.update { it.copy(error = (error as NetworkError).toUiText()) }
                }
            }
        }
    }

}