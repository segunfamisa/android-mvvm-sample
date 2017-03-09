package com.segunfamisa.sample.mvvm.data.repository;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.remote.api.ApiUtils;
import com.segunfamisa.sample.mvvm.data.remote.api.MovieApiService;
import com.segunfamisa.sample.mvvm.data.remote.responses.DiscoverMoviesResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class MoviesRemoteRepository implements MoviesRepository {

    private MovieApiService apiService;

    public MoviesRemoteRepository(MovieApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<Movie>> getPopularMovies(int page) {
        Observable<DiscoverMoviesResponse> discoverMoviesResponseObservable =
                apiService.discover("popularity.desc", page, ApiUtils.getApiKey());
        return discoverMoviesResponseObservable
                .flatMap(new Function<DiscoverMoviesResponse, ObservableSource<? extends List<Movie>>>() {
                    @Override
                    public ObservableSource<? extends List<Movie>> apply(DiscoverMoviesResponse discoverMoviesResponse)
                            throws Exception {
                        return Observable.just(discoverMoviesResponse.getResults());
                    }
                });
    }

    @Override
    public Observable<Movie> getMovieDetails(long movieId) {
        return apiService.getMovieDetails(movieId, ApiUtils.getApiKey());
    }
}
