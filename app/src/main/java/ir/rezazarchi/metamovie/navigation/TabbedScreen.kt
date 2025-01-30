package ir.rezazarchi.metamovie.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.rezazarchi.metamovie.R
import ir.rezazarchi.metamovie.core.utils.UiText
import ir.rezazarchi.metamovie.features.bookmark.presentation.ui.BookmarkedScreenRoot
import ir.rezazarchi.metamovie.features.search.presentation.ui.SearchScreenRoot

@Composable
fun TabbedScreen(
    modifier: Modifier = Modifier,
    showSnackBar: (UiText) -> Unit,
    onMovieClick: (Long) -> Unit,
) {

    val state = rememberTabbedScreenState()

    LaunchedEffect(state.selectedTabIndex) {
        state.pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    LaunchedEffect(state.pagerState.currentPage) {
        state.selectTab(state.pagerState.currentPage)
    }

    Column(modifier) {
        TabRow(
            selectedTabIndex = state.selectedTabIndex,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
        ) {
            Tab(
                selected = state.selectedTabIndex == 0,
                onClick = {
                    state.selectTab(0)
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.search_results),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
            }
            Tab(
                selected = state.selectedTabIndex == 1,
                onClick = {
                    state.selectTab(1)
                },
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(R.string.favorites),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalPager(
            state = state.pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { pageIndex ->
            when (pageIndex) {
                0 -> {
                    SearchScreenRoot(
                        showSnackBar = showSnackBar,
                        onMovieClick = onMovieClick,
                    )
                }

                1 -> {
                    BookmarkedScreenRoot(
                        onMovieClick = onMovieClick,
                    )
                }
            }
        }
    }
}