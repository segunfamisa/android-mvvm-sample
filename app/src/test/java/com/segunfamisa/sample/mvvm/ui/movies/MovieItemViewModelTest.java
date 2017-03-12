package com.segunfamisa.sample.mvvm.ui.movies;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class MovieItemViewModelTest {

    @Mock
    private Interactor interactor;

    @Mock
    private MoviesRepository mMoviesRepository;

    @Mock
    private Movie mMovie;
    private MovieItemViewModel mMovieItemViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mMovieItemViewModel = new MovieItemViewModel(mMoviesRepository, interactor);
    }

    @Test
    public void clickOnItemShowsMovieDetails() {
        // given the movie item view model has the movie set
        mMovieItemViewModel.setMovie(mMovie);

        // when the movie item is clicked
        mMovieItemViewModel.clickMovieItem();

        // then show movie details
        verify(interactor).showMovieDetails(mMovie);
    }
}
