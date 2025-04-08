package ir.rezazarchi.metamovie.features.details.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
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
    AnimatedContent(targetState = state.isLoading) { isLoading ->
        if (isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
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
                        onToggleBookmark(state.newsDetails!!.id, state.isBookmarked)
                    }) {
                        val scale by animateFloatAsState(
                            targetValue = if (state.isBookmarked) 1.2f else 1f,
                            animationSpec = tween(durationMillis = 300),
                            label = "Bookmark Scale"
                        )
                        Icon(
                            imageVector = if (state.isBookmarked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = if (state.isBookmarked) stringResource(
                                R.string.remove_from_bookmark
                            )
                            else stringResource(R.string.add_to_bookmark),
                            tint = Color.Red,
                            modifier = Modifier.graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }
                        )
                    }
                }

                val placeholder = painterResource(R.drawable.local_movies)
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10)),
                    contentScale = ContentScale.Crop,
                    painter = rememberAsyncImagePainter(
                        model = state.newsDetails?.imageUrl,
                        placeholder = placeholder,
                        error = placeholder,
                        fallback = placeholder,
                    ),
                    contentDescription = state.newsDetails?.title,
                )

                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "${state.newsDetails?.title}",
                    style = MaterialTheme.typography.titleLarge,
                )

                state.newsDetails?.fullContent?.let { Text(text = it) }
            }
        }
    }
}