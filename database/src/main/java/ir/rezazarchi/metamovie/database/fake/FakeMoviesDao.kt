package ir.rezazarchi.metamovie.database.fake

import ir.rezazarchi.metamovie.database.dao.MoviesDao
import ir.rezazarchi.metamovie.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeMoviesDao : MoviesDao {

    private val movieList = mutableListOf<MovieEntity>()
    private val moviesFlow = MutableStateFlow<List<MovieEntity>>(emptyList())

    override fun getAllMovies(): Flow<List<MovieEntity>> {
        return moviesFlow
    }

    override fun getMovieByTag(tag: String): Flow<List<MovieEntity>> {
        return flowOf(movieList.filter { it.tags.contains(tag) })
    }

    override fun getMovieById(id: Long): Flow<MovieEntity> {
        return flowOf(movieList.first { it.id == id })
    }

    override suspend fun upsertMovies(vararg movie: MovieEntity) {
        movieList.removeAll { existing -> movie.any { it.id == existing.id } }
        movieList.addAll(movie)
        moviesFlow.value = movieList.toList()
    }
}
