package com.rodrigobresan.cache.movie.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.rodrigobresan.cache.movie.model.MovieCached

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieCached)

    @Query("select Movie.id, Movie.title, Movie.rating, Movie.picture from Movie " +
            "INNER JOIN MovieCategory ON " +
            "MovieCategory.movieId = Movie.id " +
            "WHERE MovieCategory.categoryId LIKE :arg0")
    fun getAllMovies(categoryName: String): List<MovieCached>


    @Query("select * from Movie")
    fun getAllMovies(): List<MovieCached>

    @Query("select * from Movie where id = :arg0")
    fun getMovieById(id: Long): MovieCached
}