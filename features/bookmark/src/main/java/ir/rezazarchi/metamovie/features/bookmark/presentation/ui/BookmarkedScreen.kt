package ir.rezazarchi.metamovie.features.bookmark.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import ir.rezazarchi.metamovie.R
import ir.rezazarchi.metamovie.features.bookmark.presentation.viewmode.BookmarkedContracts.BookmarkedEvents
import ir.rezazarchi.metamovie.features.bookmark.presentation.viewmode.BookmarkedContracts.BookmarkedState
import ir.rezazarchi.metamovie.features.bookmark.presentation.viewmode.BookmarkedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkedScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: BookmarkedViewModel = koinViewModel(),
    onMovieClick: (Long) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    BookmarkedScreen(
        modifier = modifier,
        state = state,
        onRemoveBookmark = {
            viewModel.onEvent(BookmarkedEvents.RemoveBookmark(it))
        },
        onMovieClick = onMovieClick,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookmarkedScreen(
    modifier: Modifier = Modifier,
    state: BookmarkedState,
    onRemoveBookmark: (Long) -> Unit,
    onMovieClick: (Long) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(state.movies, key = { it.id }) { movie ->
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem()
                    .clickable {
                        onMovieClick(movie.id)
                    },
                headlineContent = {
                    Text(text = movie.userNameUploader)
                },
                supportingContent = {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        maxItemsInEachRow = 3,
                        maxLines = 1,
                    ) {
                        movie.tags.fastForEach {
                            SuggestionChip(
                                shape = RoundedCornerShape(4.dp),
                                onClick = {},
                                label = {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.labelSmall,
                                        maxLines = 1,
                                    )
                                }
                            )
                        }
                    }
                },
                leadingContent = {
                    Image(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(10)),
                        contentScale = ContentScale.Crop,
                        painter = rememberAsyncImagePainter(
                            model = movie.videoThumbnail,
                            placeholder = painterResource(R.drawable.local_movies),
                            error = painterResource(R.drawable.local_movies),
                            fallback = painterResource(R.drawable.local_movies),
                        ),
                        contentDescription = movie.tags.firstOrNull(),
                    )
                },
                trailingContent = {
                    IconButton(
                        onClick = {
                            onRemoveBookmark(movie.id)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(R.string.remove_from_bookmark),
                        )
                    }
                },
            )
            HorizontalDivider()
        }
    }
}

@Preview
@Composable
private fun BookmarkedScreenPreview() {
    Surface {
//        BookmarkedScreen(
//            state = FakeSearchScreenData.searchResultItems,
//            onRemoveBookmark = {},
//            onMovieClick = {},
//        )
    }
}