package ir.rezazarchi.metamovie.features.details.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.rezazarchi.metamovie.R
import ir.rezazarchi.metamovie.features.details.presentation.viewmode.MovieDetailsEvents.GetMovieDetails
import ir.rezazarchi.metamovie.features.details.presentation.viewmode.MovieDetailsEvents.ToggleBookmark
import ir.rezazarchi.metamovie.features.details.presentation.viewmode.MovieDetailsState
import ir.rezazarchi.metamovie.features.details.presentation.viewmode.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreenRoot(
    modifier: Modifier = Modifier,
    movieId: Long,
    onBackClicked: () -> Unit,
    viewModel: MovieDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = LocalLifecycleOwner.current.lifecycle) {
        viewModel.onEvent(GetMovieDetails(movieId))
    }

    MovieDetailsScreen(
        modifier = modifier,
        state = state,
        onToggleBookmark = { id, bookmarked ->
            viewModel.onEvent(ToggleBookmark(id, !bookmarked))
        },
        onBackClicked = onBackClicked,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    state: MovieDetailsState,
    onToggleBookmark: (Long, Boolean) -> Unit,
    onBackClicked: () -> Unit,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                    )
                }
                IconButton(onClick = {
                    onToggleBookmark(state.movieDetails!!.id, state.isBookmarked)
                }) {
                    Icon(
                        imageVector = if (state.isBookmarked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (state.isBookmarked) stringResource(
                            R.string.remove_from_bookmark
                        )
                        else stringResource(R.string.add_to_bookmark),
                        tint = Color.Red,
                    )
                }
            }

            state.movieDetails?.videoUrl?.let {
                VideoPlayer(
                    url = it,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                )
            }

            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                text = "Uploaded by: ${state.movieDetails?.userNameUploader}",
                style = MaterialTheme.typography.titleLarge,
            )

            FlowRow(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                state.movieDetails?.tags?.fastForEach {
                    SuggestionChip(
                        onClick = {},
                        label = {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                            )
                        })
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticItem(
                    icon = Icons.Default.Face,
                    count = state.movieDetails?.movieStatistics?.numberOfViews,
                    label = stringResource(R.string.views_count),
                )
                StatisticItem(
                    icon = Icons.Default.Favorite,
                    count = state.movieDetails?.movieStatistics?.numberOfLikes,
                    label = stringResource(R.string.likes_count),
                )
                StatisticItem(
                    icon = Icons.Default.MailOutline,
                    count = state.movieDetails?.movieStatistics?.numberOfComments,
                    label = stringResource(R.string.comments_count),
                )
            }
        }
    }
}