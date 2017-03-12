package com.segunfamisa.sample.mvvm.utils;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableLong;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;

/**
 * Base ViewModel for the movie.
 */
public class MovieViewModel extends BaseObservable {

    private final ObservableField<Movie> mObservableMovie = new ObservableField<>();
    private MoviesRepository moviesRepository;

    public final ObservableField<String> posterUrl = new ObservableField<>();
    public final ObservableField<String> movieTitle = new ObservableField<>();
    public final ObservableField<String> backdropUrl = new ObservableField<>();
    public final ObservableFloat voteAverage = new ObservableFloat();
    public final ObservableLong voteCount = new ObservableLong();
    public final ObservableField<String> movieOverview = new ObservableField<>();
    public final ObservableField<String> movieTagline = new ObservableField<>();
    public final ObservableField<String> movieReleaseDate = new ObservableField<>();

    public MovieViewModel(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;

        mObservableMovie.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Movie movie = mObservableMovie.get();

                if (movie != null) {
                    posterUrl.set(getPosterUrl(movie));
                    movieTitle.set(getTitle(movie));
                    backdropUrl.set(getBackdropUrl(movie));
                    voteAverage.set((float) movie.getVoteAverage());
                    voteCount.set(movie.getVoteCount());
                    movieOverview.set(movie.getOverview());
                    movieTagline.set(movie.getTagline());
                    movieReleaseDate.set(getReleaseDate(movie));
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

    private String getBackdropUrl(@NonNull Movie movie) {
        return isEmpty(movie.getBackdropPath()) ? "" :
                "https://image.tmdb.org/t/p/w780" + movie.getBackdropPath();
    }

    private String getReleaseDate(Movie movie) {
        return movie.getReleaseDate();
    }

    private boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }
}
