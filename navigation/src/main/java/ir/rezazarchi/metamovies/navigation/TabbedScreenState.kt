package ir.rezazarchi.metamovies.navigation

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

class TabbedScreenState(
    private var _selectedTabIndex: MutableIntState,
    val pagerState: PagerState,
) {
    var selectedTabIndex: Int
        get() = _selectedTabIndex.intValue
        private set(value) {
            _selectedTabIndex.intValue = value
        }

    fun selectTab(index: Int) {
        selectedTabIndex = index
    }
}

@Composable
fun rememberTabbedScreenState(): TabbedScreenState {
    val selectedTabIndex = rememberSaveable { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { 2 }

    return remember {
        TabbedScreenState(
            selectedTabIndex,
            pagerState,
        )
    }
}

