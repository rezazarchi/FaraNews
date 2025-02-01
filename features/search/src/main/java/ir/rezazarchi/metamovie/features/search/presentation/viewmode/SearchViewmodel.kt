package ir.rezazarchi.metamovie.features.search.presentation.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.metamovie.core.data.NetworkError
import ir.rezazarchi.metamovie.core.data.onError
import ir.rezazarchi.metamovie.core.data.onSuccess
import ir.rezazarchi.metamovie.core.utils.toUiText
import ir.rezazarchi.metamovie.bookmark.domain.usecase.BookmarkedMoviesUseCase
import ir.rezazarchi.metamovie.bookmark.domain.usecase.ToggleBookmarkUseCase
import ir.rezazarchi.metamovie.features.search.domain.model.SearchedMovie
import ir.rezazarchi.metamovie.features.search.domain.usecase.SearchMoviesUseCase
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchMoviesEffects.ShowSnackBar
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewmodel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val bookmarkUseCase: BookmarkedMoviesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    private val searchResultFlow = MutableStateFlow<List<SearchedMovie>>(emptyList())
    private val bookmarkedMoviesFlow = MutableStateFlow<Set<Long>>(emptySet())

    private var searchJob: Job? = null

    private val _uiEvent = Channel<SearchMoviesEffects>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(SearchMoviesState())
    val state = _state.onStart {
        observeForQuerySearch()
        observeBookmarkedList()
        loadMoviesWithBookmarks()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private fun loadMoviesWithBookmarks() {
        combine(searchResultFlow, bookmarkedMoviesFlow) { movies, bookmarkedMovies ->
            _state.update {
                it.copy(
                    isLoading = false,
                    movies = movies.map { movie ->
                        SearchedMovieWithBookmark(movie, bookmarkedMovies.contains(movie.id))
                    }
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun observeForQuerySearch() {
        _state
            .map { it.query }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                error = null,
                                movies = emptyList(),
                                isLoading = false,
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchMovies(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchMovies(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true,
                error = null,
            )
        }
        searchMoviesUseCase(query).onEach {
            it.onSuccess { searchedMovies ->
                _state.update {
                    it.copy(
                        error = null,
                        isLoading = false,
                    )
                }
                searchResultFlow.update {
                    searchedMovies
                }
            }.onError { error ->
                val errorText = (error as NetworkError).toUiText()
                _uiEvent.send(ShowSnackBar(errorText))
                _state.update {
                    it.copy(
                        error = errorText,
                        isLoading = false,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun observeBookmarkedList() {
        bookmarkUseCase()
            .distinctUntilChanged()
            .onEach { favoriteBooks ->
                favoriteBooks
                    .onSuccess { result ->
                        bookmarkedMoviesFlow.update {
                            result
                        }
                    }
                    .onError { error ->
                        _state.update {
                            _state.value.copy(
                                error = (error as NetworkError).toUiText(),
                                isLoading = false,
                            )
                        }
                    }
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: SearchMoviesEvents) {
        when (event) {

            is SearchMoviesEvents.ToggleBookmark -> {
                toggleBookmark(event.id, event.bookmarked)
            }

            is SearchMoviesEvents.OnSearchQueryChange -> {
                _state.update {
                    it.copy(query = event.query)
                }
            }

            is SearchMoviesEvents.SearchForMovies -> {
                searchMovies(event.query)
            }
        }
    }

    private fun toggleBookmark(id: Long, bookmarked: Boolean) {
        viewModelScope.launch {
            toggleBookmarkUseCase(id, bookmarked)
        }
    }

}