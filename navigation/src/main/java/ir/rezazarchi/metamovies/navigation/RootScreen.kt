package ir.rezazarchi.metamovies.navigation

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
import ir.rezazarchi.metamovie.features.details.presentation.ui.MovieDetailsScreenRoot
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
            startDestination = NavigationRoute.MovieList,
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally() },
        ) {
            composable<NavigationRoute.MovieList> {
                TabbedScreen(
                    modifier = Modifier.padding(paddingValues),
                    showSnackBar = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it.asString(context))
                        }
                    },
                    onMovieClick = {
                        navController.navigate(NavigationRoute.MovieDetails(it))
                    },
                )
            }
            composable<NavigationRoute.MovieDetails> {
                val movieId = it.toRoute<NavigationRoute.MovieDetails>().id
                MovieDetailsScreenRoot(
                    modifier = Modifier.padding(paddingValues),
                    movieId = movieId,
                    onBackClicked = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}