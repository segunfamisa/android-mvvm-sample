package com.segunfamisa.sample.mvvm.ui.moviedetails;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.remote.api.ApiUtils;
import com.segunfamisa.sample.mvvm.data.remote.api.MovieApiService;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.utils.TestDataGenerator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailsViewModelTest {
    private static final String API_KEY = ApiUtils.getApiKey();
    private static final long MOVIE_ID = 1;

    private Movie mMovie;

    @Mock
    private MoviesRepository mMovieRepository;

    @Mock
    private MovieApiService mApiService;

    private MovieDetailsViewModel mDetailsViewModel;

    @BeforeClass
    public static void setupRxJavaPlugins() {
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // create dummy movie
        mMovie = TestDataGenerator.generateMovie(MOVIE_ID);

        // create movie details view model
        mDetailsViewModel = new MovieDetailsViewModel(mMovieRepository);
    }

    @Test
    public void getMovieDetailsWithoutError() {
        // given a movie repository
        setupRepository();

        // when get movie details is called
        mDetailsViewModel.getMovieDetails(MOVIE_ID);

        // then verify that the repository makes api call
        verify(mMovieRepository).getMovieDetails(MOVIE_ID);

        // assert that the loading indicator and error view are not visible
        assertFalse(mDetailsViewModel.isMovieLoading.get());
        assertFalse(mDetailsViewModel.errorViewShowing.get());

        // verify movie fieldss
        verifyThatMovieFieldsAreSet(mDetailsViewModel, mMovie);
    }

    @Test
    public void errorViewIsShownWhenGetMovieDetailsGivesError() {
        // given that the movie repository returns an error
        when(mMovieRepository.getMovieDetails(MOVIE_ID)).thenReturn(
                Observable.<Movie>error(new Throwable("Exception")));

        // when get movie details is called
        mDetailsViewModel.getMovieDetails(MOVIE_ID);

        // then verify that the error view is visible and the loading indicator is gone
        assertTrue(mDetailsViewModel.errorViewShowing.get());
        assertFalse(mDetailsViewModel.isMovieLoading.get());
    }

    private void verifyThatMovieFieldsAreSet(MovieDetailsViewModel detailsViewModel, Movie movie) {
        assertEquals(detailsViewModel.movieTitle.get(), movie.getTitle());
        assertTrue(detailsViewModel.posterUrl.get().contains(movie.getPosterPath()));
        assertTrue(detailsViewModel.backdropUrl.get().contains(movie.getBackdropPath()));
        assertEquals(detailsViewModel.voteAverage.get(), (float) movie.getVoteAverage());
        assertEquals(detailsViewModel.voteCount.get(), movie.getVoteCount());
        assertEquals(detailsViewModel.movieOverview.get(), movie.getOverview());
        assertEquals(detailsViewModel.movieTagline.get(), movie.getTagline());
    }

    private void setupRepository() {
        // given that the API service returns a movie observable when getMovieDetails is called
        when(mMovieRepository.getMovieDetails(MOVIE_ID)).thenReturn(Observable.just(mMovie));
    }


}
