package ir.rezazarchi.faranews.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ir.rezazarchi.faranews.features.details.presentation.ui.NewsDetailsScreenRoot
import kotlinx.coroutines.launch

@Composable
fun RootScreen(modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        topBar = {},
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.NewsList,
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally() },
        ) {
            composable<NavigationRoute.NewsList> {
                TabbedScreen(
                    modifier = Modifier.padding(paddingValues),
                    showSnackBar = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it.asString(context))
                        }
                    },
                    onItemClicked = {
                        navController.navigate(NavigationRoute.NewsDetails(it))
                    },
                )
            }
            composable<NavigationRoute.NewsDetails> {
                val newsId = it.toRoute<NavigationRoute.NewsDetails>().id
                NewsDetailsScreenRoot(
                    modifier = Modifier.padding(paddingValues),
                    newsID = newsId,
                    onBackClicked = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}