package com.segunfamisa.sample.mvvm.ui.movies;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.utils.ErrorResolver;
import com.segunfamisa.sample.mvvm.utils.SchedulerProvider;
import com.segunfamisa.sample.mvvm.utils.TestDataGenerator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoviesListViewModelTest {

    @Rule
    public TestRule instantExecutorRule = new InstantTaskExecutorRule();

    // class under test
    private MoviesListViewModel viewModel;
    //

    // constructor objects

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    private SchedulerProvider schedulerProvider;

    @Mock
    private ErrorResolver errorResolver;

    //

    @Mock
    private Observer<List<Movie>> movieListObserver;

    @Mock
    private Observer<Boolean> loadingObserver;

    @Mock
    private Observer<String> errorObserver;

    @Mock
    private Observer<Boolean> emptyObserver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // set up scheduler provider
        setUpSchedulerProvider();

        viewModel = new MoviesListViewModel(moviesRepository, schedulerProvider, errorResolver);
    }

    @Test
    public void getMoviesReturnsNonEmptyList() {
        // given that the repository is set up to return a list of movies for page 1
        final int page = 1;
        List<Movie> movies = TestDataGenerator.generateMovieList(10);
        when(moviesRepository.getPopularMovies(page)).thenReturn(Observable.just(movies));

        // given that we're observing the states
        viewModel.movies().observeForever(movieListObserver);
        viewModel.loading().observeForever(loadingObserver);
        viewModel.empty().observeForever(emptyObserver);
        viewModel.error().observeForever(errorObserver);

        // when we call get movies
        viewModel.fetchMovies(page);

        // then verify that the view model emits the expected movies.
        verify(movieListObserver).onChanged(movies);

        // then verify that the loading state shows first and then hides later
        verify(loadingObserver).onChanged(true);
        verify(loadingObserver).onChanged(false);

        // then verify that the empty state is hidden
        verify(emptyObserver, times(2)).onChanged(false);

        // then verify that the error is not shown
        verify(errorObserver).onChanged(null);
    }

    @Test
    public void getMoviesReturnsEmptyList() {
        // given that the repository is set up to return a list of movies for page 1
        final int page = 1;
        List<Movie> movies = new ArrayList<>();
        when(moviesRepository.getPopularMovies(page)).thenReturn(Observable.just(movies));

        // given that we're observing the states
        viewModel.empty().observeForever(emptyObserver);
        viewModel.error().observeForever(errorObserver);

        // when we call get movies
        viewModel.fetchMovies(page);

        // then verify that the empty state is hidden at first and shown later
        verify(emptyObserver).onChanged(false);
        verify(emptyObserver).onChanged(true);

        // then verify that the error is not shown
        verify(errorObserver).onChanged(null);
    }

    @Test
    public void getMoviesReturnsError() {
        final int page = 1;
        final String error = "Error message";

        // given that the error resolver returns error
        when(errorResolver.getErrorMessage(any(Throwable.class))).thenReturn(error);

        // given that the repository is set up to return an error
        when(moviesRepository.getPopularMovies(page))
                .thenReturn(Observable.<List<Movie>>error(new Throwable(error)));

        // given that we're observing the states
        viewModel.loading().observeForever(loadingObserver);
        viewModel.error().observeForever(errorObserver);

        // when we call get movies
        viewModel.fetchMovies(page);

        // then verify that the loading state is first visible and then hidden
        verify(loadingObserver).onChanged(true);
        verify(loadingObserver).onChanged(false);

        // then verify that the error state is first hidden and then is visible on error
        verify(errorObserver).onChanged(null);
        verify(errorObserver).onChanged(error);
    }

    private void setUpSchedulerProvider() {
        when(schedulerProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulerProvider.mainThread()).thenReturn(Schedulers.trampoline());
    }
}