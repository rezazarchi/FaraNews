package ir.rezazarchi.faranews.features.search.presentation.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.faranews.core.data.NetworkError
import ir.rezazarchi.faranews.core.data.onError
import ir.rezazarchi.faranews.core.data.onSuccess
import ir.rezazarchi.faranews.core.utils.toUiText
import ir.rezazarchi.faranews.bookmark.domain.usecase.BookmarkedNewsUseCase
import ir.rezazarchi.faranews.bookmark.domain.usecase.ToggleBookmarkUseCase
import ir.rezazarchi.faranews.features.search.domain.model.SearchedNews
import ir.rezazarchi.faranews.features.search.domain.usecase.SearchNewsUseCase
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchNewsEffects.ShowSnackBar
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewmodel(
    private val searchNewsUseCase: SearchNewsUseCase,
    private val bookmarkUseCase: BookmarkedNewsUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    private val searchResultFlow = MutableStateFlow<List<SearchedNews>>(emptyList())
    private val bookmarkedNewsFlow = MutableStateFlow<Set<Long>>(emptySet())

    private var searchJob: Job? = null

    private val _uiEvent = Channel<SearchNewsEffects>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(SearchNewsState())
    val state = _state.onStart {
        fetchNews()
        observeBookmarkedList()
        loadNewsWithBookmarks()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private fun loadNewsWithBookmarks() {
        combine(searchResultFlow, bookmarkedNewsFlow) { news, bookmarkedNews ->
            _state.update {
                it.copy(
                    isLoading = false,
                    news = news.map { news ->
                        SearchedNewsWithBookmark(news, bookmarkedNews.contains(news.id))
                    }
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun fetchNews() = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true,
                error = null,
            )
        }
        searchNewsUseCase().onEach {
            it.onSuccess { searchedNews ->
                _state.update {
                    it.copy(
                        error = null,
                        isLoading = false,
                    )
                }
                searchResultFlow.update {
                    searchedNews
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
                        bookmarkedNewsFlow.update {
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

    fun onEvent(event: SearchNewsEvents) {
        when (event) {

            is SearchNewsEvents.ToggleBookmark -> {
                toggleBookmark(event.id, event.bookmarked)
            }

            is SearchNewsEvents.OnSearchQueryChange -> {
                _state.update {
                    it.copy()
                }
            }

            is SearchNewsEvents.SearchForNews -> {
                fetchNews()
            }
        }
    }

    private fun toggleBookmark(id: Long, bookmarked: Boolean) {
        viewModelScope.launch {
            toggleBookmarkUseCase(id, bookmarked)
        }
    }

}