package ir.rezazarchi.faranews.features.search.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.rezazarchi.faranews.commonui.components.EmptyListPlaceHolder
import ir.rezazarchi.faranews.core.utils.Constant.SEARCH_QUERIES
import ir.rezazarchi.faranews.core.utils.ObserveAsEvents
import ir.rezazarchi.faranews.core.utils.UiText
import ir.rezazarchi.faranews.features.search.presentation.fake.FakeSearchScreenData
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchMoviesEffects
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchMoviesEvents
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchMoviesState
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchViewmodel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SearchScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SearchViewmodel = koinViewModel(),
    showSnackBar: (UiText) -> Unit,
    onItemClicked: (Long) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.uiEvent) {
        when (it) {
            is SearchMoviesEffects.ShowSnackBar -> showSnackBar(it.message)
        }
    }

    SearchScreen(
        modifier = modifier,
        state = state,
        onToggleBookmark = { id, bookmarked ->
            viewModel.onEvent(SearchMoviesEvents.ToggleBookmark(id, bookmarked))
        },
        loadListData = {
            viewModel.onEvent(SearchMoviesEvents.SearchForMovies)
        },
        onItemClicked = onItemClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchMoviesState,
    onToggleBookmark: (Long, Boolean) -> Unit,
    loadListData: () -> Unit,
    onItemClicked: (Long) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { loadListData() },
        ) {
            AnimatedContent(targetState = state.movies.isEmpty()) { isListEmpty ->
                if (isListEmpty) {
                    EmptyListPlaceHolder(
                        modifier = Modifier,
                        icon = Icons.AutoMirrored.Outlined.List,
                        title = stringResource(ir.rezazarchi.faranews.features.search.R.string.empty_search_list_title),
                        subtitle = stringResource(ir.rezazarchi.faranews.features.search.R.string.empty_search_list_subtitle),
                    )
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            items = state.movies.sortedBy {
                                SEARCH_QUERIES.indexOf(it.searchedNews.queryName)
                            },
                            key = { it.searchedNews.id }) { movie ->
                            NewsListItem(
                                newsWithBookmarkState = movie,
                                onItemClicked = onItemClicked,
                                onToggleBookmark = onToggleBookmark,
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSearchScreen() {
    Surface {
        SearchScreen(
            state = FakeSearchScreenData.searchResultItems,
            onToggleBookmark = { _, _ -> },
            loadListData = {},
            onItemClicked = {},
        )
    }
}