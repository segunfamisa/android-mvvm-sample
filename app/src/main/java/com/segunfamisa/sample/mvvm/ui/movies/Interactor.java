package com.segunfamisa.sample.mvvm.ui.movies;

import com.segunfamisa.sample.mvvm.data.model.Movie;

/**
 * Interface to define interaction contracts for the movies list
 */
interface Interactor {

    void showMovieDetails(Movie movie);

}
