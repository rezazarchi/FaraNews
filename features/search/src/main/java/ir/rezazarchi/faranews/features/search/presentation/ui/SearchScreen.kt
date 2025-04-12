package ir.rezazarchi.faranews.features.search.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.rezazarchi.faranews.core.utils.Constant.SEARCH_QUERIES
import ir.rezazarchi.faranews.core.utils.ObserveAsEvents
import ir.rezazarchi.faranews.core.utils.UiText
import ir.rezazarchi.faranews.features.search.presentation.fake.FakeSearchScreenData
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchNewsEffects
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchNewsEvents
import ir.rezazarchi.faranews.features.search.presentation.viewmode.SearchNewsState
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
            is SearchNewsEffects.ShowSnackBar -> showSnackBar(it.message)
        }
    }

    SearchScreen(
        modifier = modifier,
        state = state,
        onToggleBookmark = { id, bookmarked ->
            viewModel.onEvent(SearchNewsEvents.ToggleBookmark(id, bookmarked))
        },
        loadListData = {
            viewModel.onEvent(SearchNewsEvents.SearchForNews)
        },
        onItemClicked = onItemClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchNewsState,
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
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            items = state.news.sortedBy {
                                SEARCH_QUERIES.indexOf(it.searchedNews.queryName)
                            },
                            key = { it.searchedNews.id }) { news ->
                            NewsListItem(
                                newsWithBookmarkState = news,
                                onItemClicked = onItemClicked,
                                onToggleBookmark = onToggleBookmark,
                            )
                            HorizontalDivider()
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