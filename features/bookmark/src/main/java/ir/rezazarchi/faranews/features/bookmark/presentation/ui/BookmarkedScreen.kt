package ir.rezazarchi.faranews.features.bookmark.presentation.ui

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import ir.rezazarchi.faranews.commonui.components.EmptyListPlaceHolder
import ir.rezazarchi.faranews.features.bookmark.R
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedContracts.BookmarkedEvents
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedContracts.BookmarkedState
import ir.rezazarchi.faranews.features.bookmark.presentation.viewmode.BookmarkedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkedScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: BookmarkedViewModel = koinViewModel(),
    onNewsClick: (Long) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    BookmarkedScreen(
        modifier = modifier,
        state = state,
        onRemoveBookmark = {
            viewModel.onEvent(BookmarkedEvents.RemoveBookmark(it))
        },
        onNewsClick = onNewsClick,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookmarkedScreen(
    modifier: Modifier = Modifier,
    state: BookmarkedState,
    onRemoveBookmark: (Long) -> Unit,
    onNewsClick: (Long) -> Unit,
) {
    AnimatedContent(targetState = state.news.isEmpty()) { isListEmpty ->
        if (isListEmpty) {
            EmptyListPlaceHolder(
                modifier = Modifier,
                icon = Icons.Outlined.Info,
                title = stringResource(R.string.empty_bookmark_list_title),
                subtitle = stringResource(R.string.empty_bookmark_list_subtitle),
            )
        } else {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(state.news, key = { it.id }) { news ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem()
                            .clickable {
                                onNewsClick(news.id)
                            },
                        headlineContent = {
                            Text(text = news.title)
                        },
                        supportingContent = {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                maxItemsInEachRow = 3,
                                maxLines = 1,
                            ) {
                                Text(text = news.fullContent)
                            }
                        },
                        leadingContent = {
                            val placeholder = painterResource(ir.rezazarchi.faranews.R.drawable.baseline_newspaper)
                            Image(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(10)),
                                contentScale = ContentScale.Crop,
                                painter = rememberAsyncImagePainter(
                                    model = news.imageUrl,
                                    placeholder = placeholder,
                                    error = placeholder,
                                    fallback = placeholder,
                                ),
                                contentDescription = news.title,
                            )
                        },
                        trailingContent = {
                            IconButton(
                                onClick = {
                                    onRemoveBookmark(news.id)
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
    }
}

@Preview
@Composable
private fun BookmarkedScreenPreview() {
    Surface {
//        BookmarkedScreen(
//            state = FakeSearchScreenData.searchResultItems,
//            onRemoveBookmark = {},
//            onNewsClick = {},
//        )
    }
}