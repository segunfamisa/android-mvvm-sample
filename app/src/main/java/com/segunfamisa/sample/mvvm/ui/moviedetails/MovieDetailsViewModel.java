package com.segunfamisa.sample.mvvm.ui.moviedetails;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.utils.MovieViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsViewModel extends MovieViewModel {

    public final ObservableBoolean isMovieLoading = new ObservableBoolean();
    public final ObservableBoolean errorViewShowing = new ObservableBoolean();
    public final ObservableField<String> errorString = new ObservableField<>();

    private final MoviesRepository mMoviesRepository;

    public MovieDetailsViewModel(MoviesRepository moviesRepository) {
        super(moviesRepository);
        mMoviesRepository = moviesRepository;
    }

    public void getMovieDetails(long movieId) {
        isMovieLoading.set(true);
        errorViewShowing.set(false);
        mMoviesRepository.getMovieDetails(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie value) {
                        setMovie(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorViewShowing.set(true);
                        isMovieLoading.set(false);
                    }

                    @Override
                    public void onComplete() {
                        isMovieLoading.set(false);
                        errorViewShowing.set(false);
                    }
                });
    }
}
