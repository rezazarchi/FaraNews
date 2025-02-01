package ir.rezazarchi.metamovie.features.search.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import ir.rezazarchi.metamovie.R
import ir.rezazarchi.metamovie.core.utils.ObserveAsEvents
import ir.rezazarchi.metamovie.core.utils.UiText
import ir.rezazarchi.metamovie.features.search.presentation.fake.FakeSearchScreenData
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchMoviesEffects
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchMoviesEvents
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchMoviesState
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchViewmodel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SearchScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SearchViewmodel = koinViewModel(),
    showSnackBar: (UiText) -> Unit,
    onMovieClick: (Long) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
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
            viewModel.onEvent(SearchMoviesEvents.SearchForMovies(it))
        },
        onMovieClick = onMovieClick,
        onTagClicked = {
            viewModel.onEvent(SearchMoviesEvents.OnSearchQueryChange(it))
        },
        onSearchQueryChanged = {
            viewModel.onEvent(SearchMoviesEvents.OnSearchQueryChange(it))
        },
        onImeSearch = {
            keyboardController?.hide()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchMoviesState,
    onToggleBookmark: (Long, Boolean) -> Unit,
    loadListData: (String) -> Unit,
    onMovieClick: (Long) -> Unit,
    onTagClicked: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onImeSearch: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBox(
            searchQuery = state.query,
            onSearchQueryChange = onSearchQueryChanged,
            onImeSearch = onImeSearch,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { loadListData(state.query) },
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies, key = { it.searchedMovie.id }) { movie ->
                    val searchedMovie = movie.searchedMovie
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem()
                            .clickable {
                                onMovieClick(searchedMovie.id)
                            },
                        headlineContent = {
                            Text(text = searchedMovie.userNameUploader)
                        },
                        supportingContent = {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                maxItemsInEachRow = 3,
                                maxLines = 1,
                            ) {
                                searchedMovie.tags.fastForEach {
                                    SuggestionChip(
                                        shape = RoundedCornerShape(4.dp),
                                        onClick = {
                                            onTagClicked(it)
                                        },
                                        label = {
                                            Text(
                                                text = it,
                                                style = MaterialTheme.typography.labelSmall,
                                                maxLines = 1,
                                            )
                                        })
                                }
                            }
                        },
                        leadingContent = {
                            Image(
                                modifier = Modifier
                                    .size(56.dp)
                                    .align(Alignment.Center)
                                    .clip(RoundedCornerShape(10)),
                                contentScale = ContentScale.Crop,
                                painter = rememberAsyncImagePainter(
                                    model = searchedMovie.videoThumbnail,
                                    placeholder = painterResource(R.drawable.local_movies),
                                    error = painterResource(R.drawable.local_movies),
                                    fallback = painterResource(R.drawable.local_movies),
                                ),
                                contentDescription = searchedMovie.tags.firstOrNull(),
                            )
                        },
                        trailingContent = {
                            IconButton(
                                onClick = {
                                    onToggleBookmark(searchedMovie.id, !movie.bookmarked)
                                },
                            ) {
                                Icon(
                                    imageVector = if (movie.bookmarked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = if (movie.bookmarked) stringResource(
                                        R.string.remove_from_bookmark
                                    )
                                    else stringResource(R.string.add_to_bookmark),
                                    tint = Color.Red,
                                )
                            }
                        },
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
            onMovieClick = {},
            onTagClicked = { },
            onSearchQueryChanged = { },
            onImeSearch = { },
        )
    }
}