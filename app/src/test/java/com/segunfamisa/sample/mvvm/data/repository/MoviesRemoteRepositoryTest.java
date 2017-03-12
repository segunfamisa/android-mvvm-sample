package com.segunfamisa.sample.mvvm.data.repository;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.remote.api.ApiUtils;
import com.segunfamisa.sample.mvvm.data.remote.api.MovieApiService;
import com.segunfamisa.sample.mvvm.data.remote.responses.DiscoverMoviesResponse;
import com.segunfamisa.sample.mvvm.utils.TestDataGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoviesRemoteRepositoryTest {

    private static final String SORT_BY_POPULARITY = "popularity.desc";
    private static final int PAGE = 1;
    private static final String API_KEY = ApiUtils.getApiKey();
    private static final long MOVIE_ID = 1;


    private List<Movie> mMovieList;
    private DiscoverMoviesResponse mDiscoverMoviesResponse;

    private MoviesRepository mRemoteRepository;

    private TestObserver<List<Movie>> mMovieListTestSubscriber;
    private TestObserver<Movie> mMovieTestSubscriber;

    @Mock
    MovieApiService apiService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mRemoteRepository = new MoviesRemoteRepository(apiService);

        mMovieTestSubscriber = new TestObserver<>();
        mMovieListTestSubscriber = new TestObserver<>();

        mMovieList = TestDataGenerator.generateMovieList(10);
        mDiscoverMoviesResponse = TestDataGenerator.generateDiscoverMoviesResponse(mMovieList);
    }

    @Test
    public void getPopularMoviesMakesApiCall() {
        // given that the api service returns a response
        when(apiService.discover(SORT_BY_POPULARITY, PAGE, API_KEY))
                .thenReturn(Observable.just(mDiscoverMoviesResponse));

        // when getPopularMovies is invoked
        mRemoteRepository.getPopularMovies(1).subscribeWith(mMovieListTestSubscriber);

        // then, verify that the api request is made and returns the expected response
        verify(apiService).discover(SORT_BY_POPULARITY, PAGE, API_KEY);
        mMovieListTestSubscriber.assertValue(mMovieList);
    }

    @Test
    public void getMovieDetails() {
        Movie movie = mMovieList.get(1);
        // given that the api service
        when(apiService.getMovieDetails(MOVIE_ID, API_KEY)).thenReturn(Observable.just(movie));

        // when getMovieDetails is invoked
        mRemoteRepository.getMovieDetails(MOVIE_ID).subscribeWith(mMovieTestSubscriber);

        // then verify that the api service is called and that it returns expected movie
        verify(apiService).getMovieDetails(MOVIE_ID, API_KEY);
        mMovieTestSubscriber.assertValue(movie);

    }

}
