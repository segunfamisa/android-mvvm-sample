package com.segunfamisa.sample.mvvm.data.repository;

import com.segunfamisa.sample.mvvm.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface MoviesRepository {

    /*
     * Get list of popular movies
     */
    Observable<List<Movie>> getPopularMovies(int page);

    /**
     * Get movie details given a movie id
     */
    Observable<Movie> getMovieDetails(long movieId);

}
