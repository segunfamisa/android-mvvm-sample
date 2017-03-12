package com.segunfamisa.sample.mvvm.utils;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.remote.responses.DiscoverMoviesResponse;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    /**
     * Generates a dummy movie with id "id"
     *
     * @param id - id of the movie to generate
     */
    public static Movie generateMovie(long id) {
        return new Movie.Builder()
                .setAdult(false)
                .setBackdropPath("backdroppath")
                .setPosterPath("posterpath")
                .setBudget(10000)
                .setHomepageUrl("https://google.com")
                .setId(id)
                .setImdbId("imdbId01")
                .setTitle("Title " + id)
                .setVoteAverage(4.5)
                .setVoteCount(1000)
                .setReleaseDate("2017-02-28")
                .setOverview("This is some awesome overview")
                .build();
    }

    /**
     * Generates a list of Movies with {count} items
     * @param count - number of dummy movies to generate
     */
    public static List<Movie> generateMovieList(int count) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            movies.add(generateMovie(i));
        }
        return movies;
    }

    /**
     * Generates a {@link DiscoverMoviesResponse} object/
     *
     * @param results - the results to include in the response object
     * @return {@link DiscoverMoviesResponse} with the results set
     */
    public static DiscoverMoviesResponse generateDiscoverMoviesResponse(List<Movie> results) {
        DiscoverMoviesResponse response = new DiscoverMoviesResponse();
        response.setResults(results);
        return response;
    }
}
