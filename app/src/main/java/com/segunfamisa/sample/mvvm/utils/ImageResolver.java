package com.segunfamisa.sample.mvvm.utils;

import android.support.annotation.NonNull;

import com.segunfamisa.sample.mvvm.data.model.Movie;

/**
 * Builds image url from url
 */
public class ImageResolver {

    public static String getPosterUrl(@NonNull Movie movie) {
        return "https://image.tmdb.org/t/p/w342" + movie.getPosterPath();
    }

    public static String getBackdropUrl(@NonNull Movie movie) {
        return isEmpty(movie.getBackdropPath()) ? ""
                : "https://image.tmdb.org/t/p/w780" + movie.getBackdropPath();
    }

    private static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }
}
