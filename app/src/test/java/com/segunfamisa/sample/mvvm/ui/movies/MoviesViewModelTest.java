package com.segunfamisa.sample.mvvm.ui.movies;

import android.content.Context;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.utils.TestDataGenerator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoviesViewModelTest {
    private static final List<Movie> MOVIES = TestDataGenerator.generateMovieList(10);;
    private static final List<Movie> EMPTY_MOVIES = new ArrayList<>();
    private static final String ORDER_BY_POPULARITY = "popularity.desc";
    private static final int PAGE = 1;

    @Mock
    private MoviesRepository mMoviesRepository;

    @Mock
    private Context mContext;

    private MoviesViewModel mMoviesViewModel;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // setup context
        setupContext();
        mMoviesViewModel = new MoviesViewModel(mContext, mMoviesRepository);
    }

    private void setupContext() {
        when(mContext.getApplicationContext()).thenReturn(mContext);
    }


    /**
     * Test that the call to get popular movies returns without error
     */
    @Test
    public void getPopularMoviesWithoutError() {
        // given the following movies
        when(mMoviesRepository.getPopularMovies(PAGE)).thenReturn(Observable.just(MOVIES));

        // discover popular movies
        mMoviesViewModel.discoverMovies(true);

        // verify that the repository is called
        verify(mMoviesRepository).getPopularMovies(PAGE);

        // test that the loading indicator is hidden
        assertFalse(mMoviesViewModel.moviesLoading.get());

        // check that the empty view is hidden
        assertFalse(mMoviesViewModel.emptyViewShowing.get());

        // check that the error view is hidden
        assertFalse(mMoviesViewModel.errorViewShowing.get());

        assertTrue(mMoviesViewModel.movies.size() == MOVIES.size());
    }


    @Test
    public void getPopularMoviesReturnsEmptyList() {
        // given
        when(mMoviesRepository.getPopularMovies(PAGE)).thenReturn(Observable.just(EMPTY_MOVIES));

        // request movies
        mMoviesViewModel.discoverMovies(true);

        verify(mMoviesRepository).getPopularMovies(PAGE);

        // check that the empty view is showing
        assertTrue(mMoviesViewModel.emptyViewShowing.get());

        // check that the loading view is hidden
        assertFalse(mMoviesViewModel.moviesLoading.get());

        // check that the error view is hidden
        assertFalse(mMoviesViewModel.errorViewShowing.get());
    }

    @Test
    public void getPopularMoviesThrowsError() {
        when(mMoviesRepository.getPopularMovies(PAGE)).thenReturn(
                Observable.<List<Movie>>error(new TimeoutException()));

        // request movies
        mMoviesViewModel.discoverMovies(true);

        verify(mMoviesRepository).getPopularMovies(PAGE);

        // check that empty view is hidden
        assertFalse(mMoviesViewModel.emptyViewShowing.get());

        // check that loading view is hidden
        assertFalse(mMoviesViewModel.moviesLoading.get());

        // check that error view is showing
        assertTrue(mMoviesViewModel.errorViewShowing.get());
    }
}
