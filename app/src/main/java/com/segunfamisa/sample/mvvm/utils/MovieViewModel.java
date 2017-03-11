package com.segunfamisa.sample.mvvm.utils;

import android.support.annotation.NonNull;

import com.segunfamisa.sample.mvvm.data.model.Movie;

/**
 * Base ViewModel for the movie.
 */
public class MovieViewModel {

    private Movie movie;

    public MovieViewModel(@NonNull Movie movie) {
        this.movie = movie;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    /**
     * Constructs the poster url
     * @return the poster url
     */
    public String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w342" + movie.getPosterPath();
    }
}
