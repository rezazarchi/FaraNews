package ir.rezazarchi.faranews.features.details.presentation.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import ir.rezazarchi.faranews.R
import ir.rezazarchi.faranews.commonui.utils.formatDate
import ir.rezazarchi.faranews.features.details.presentation.viewmode.NewsDetailsEvents.GetNewsDetails
import ir.rezazarchi.faranews.features.details.presentation.viewmode.NewsDetailsEvents.ToggleBookmark
import ir.rezazarchi.faranews.features.details.presentation.viewmode.NewsDetailsState
import ir.rezazarchi.faranews.features.details.presentation.viewmode.NewsDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsDetailsScreenRoot(
    modifier: Modifier = Modifier,
    newsID: Long,
    onBackClicked: () -> Unit,
    viewModel: NewsDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = LocalLifecycleOwner.current.lifecycle) {
        viewModel.onEvent(GetNewsDetails(newsID))
    }

    NewsDetailsScreen(
        modifier = modifier,
        state = state,
        onToggleBookmark = { id, bookmarked ->
            viewModel.onEvent(ToggleBookmark(id, !bookmarked))
        },
        onBackClicked = onBackClicked,
    )
}

@Composable
fun NewsDetailsScreen(
    modifier: Modifier = Modifier,
    state: NewsDetailsState,
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

                val placeholder = painterResource(R.drawable.baseline_newspaper)
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentScale = ContentScale.FillWidth,
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

                HorizontalDivider(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 8.dp),
                )

                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "From: ${state.newsDetails?.author}",
                    style = MaterialTheme.typography.labelMedium,
                )

                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "Source: ${state.newsDetails?.source}",
                    style = MaterialTheme.typography.labelMedium,
                )

                state.newsDetails?.date?.let {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        text = "Published at: ${formatDate(it)}",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 8.dp),
                )

                state.newsDetails?.fullContent?.let {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        text =
                            HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                    )
                }
            }
        }
    }
}