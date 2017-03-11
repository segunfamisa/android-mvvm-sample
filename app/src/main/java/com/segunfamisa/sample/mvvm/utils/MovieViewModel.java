package com.segunfamisa.sample.mvvm.utils;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;

/**
 * Base ViewModel for the movie.
 */
public class MovieViewModel extends BaseObservable {

    public final ObservableField<String> posterUrl = new ObservableField<>();
    public final ObservableField<String> movieTitle = new ObservableField<>();

    private final ObservableField<Movie> mObservableMovie = new ObservableField<>();

    private MoviesRepository moviesRepository;

    public MovieViewModel(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;

        mObservableMovie.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Movie movie = mObservableMovie.get();

                if (movie != null) {
                    posterUrl.set(getPosterUrl(movie));
                    movieTitle.set(getTitle(movie));
                }
            }
        });
    }

    public void setMovie(Movie movie) {
        mObservableMovie.set(movie);
    }

    @Nullable
    public Movie getMovie() {
        return mObservableMovie.get();
    }


    private String getTitle(@NonNull Movie movie) {
        return movie.getTitle();
    }

    /**
     * Constructs the poster url
     * @return the poster url
     */
    private String getPosterUrl(@NonNull Movie movie) {
        return "https://image.tmdb.org/t/p/w342" + movie.getPosterPath();
    }
}
