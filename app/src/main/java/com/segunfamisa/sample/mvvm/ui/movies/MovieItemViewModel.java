package com.segunfamisa.sample.mvvm.ui.movies;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.utils.MovieViewModel;

public class MovieItemViewModel extends MovieViewModel {

    private Movie movie;
    private Interactor interactor;

    public MovieItemViewModel(Movie movie, Interactor interactor) {
        super(movie);
        this.movie = movie;
        this.interactor = interactor;
    }

    public void clickMovieItem() {
        interactor.showMovieDetails(movie);
    }
}
