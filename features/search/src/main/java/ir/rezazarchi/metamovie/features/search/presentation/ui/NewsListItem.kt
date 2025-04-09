package ir.rezazarchi.metamovie.features.search.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ir.rezazarchi.metamovie.R
import ir.rezazarchi.metamovie.commonui.utils.formatDate
import ir.rezazarchi.metamovie.features.search.presentation.viewmode.SearchedMovieWithBookmark

@Composable
fun NewsListItem(
    modifier: Modifier = Modifier,
    newsWithBookmarkState: SearchedMovieWithBookmark,
    onItemClicked: (Long) -> Unit,
    onToggleBookmark: (Long, Boolean) -> Unit,
) {
    val news = newsWithBookmarkState.searchedNews
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked(news.id)
            }
            .padding(12.dp)
    ) {
        AsyncImage(
            model = news.imageUrl,
            contentDescription = news.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = news.title ?: "No Title",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { onToggleBookmark(news.id, !newsWithBookmarkState.bookmarked) }
                ) {
                    Icon(
                        imageVector = if (newsWithBookmarkState.bookmarked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (newsWithBookmarkState.bookmarked) stringResource(
                            R.string.remove_from_bookmark
                        )
                        else stringResource(R.string.add_to_bookmark),
                        tint = Color.Red,
                    )
                }
            }

            Text(
                text = news.shortBrief ?: "",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = news.queryName,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = formatDate(news.date),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

