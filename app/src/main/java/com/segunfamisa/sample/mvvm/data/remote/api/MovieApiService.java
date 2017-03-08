package com.segunfamisa.sample.mvvm.data.remote.api;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.remote.responses.DiscoverMoviesResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {

    /**
     * Discover movie
     */
    @GET("discover/movie")
    Observable<DiscoverMoviesResponse> discover(@Query("sort_by") String sortBy,
                                                @Query("page") int page,
                                                @Query("api_key") String apiKey);

    /**
     * Get movie details
     *
     * @param movieId - movie id
     * @param apiKey - API key
     */
    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetails(@Path("movie_id") long movieId,
                                      @Query("api_key") String apiKey);


    class Creator {

        public static MovieApiService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ApiUtils.getBaseUrl())
                    .build();
            return retrofit.create(MovieApiService.class);
        }
    }
}
