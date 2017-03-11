package com.segunfamisa.sample.mvvm.ui.movies;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.utils.MovieViewModel;

public class MovieItemViewModel extends MovieViewModel {

    private Interactor interactor;

    public MovieItemViewModel(MoviesRepository moviesRepository, Interactor interactor) {
        super(moviesRepository);
        this.interactor = interactor;
    }

    public void clickMovieItem() {
        Movie movie = getMovie();
        if (movie != null) {
            interactor.showMovieDetails(movie);
        }
    }
}
