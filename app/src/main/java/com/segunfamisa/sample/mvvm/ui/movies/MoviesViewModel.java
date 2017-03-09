package com.segunfamisa.sample.mvvm.ui.movies;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MoviesViewModel extends BaseObservable {

    // observable fields on the
    public final ObservableList<Movie> movies = new ObservableArrayList<>();

    public final ObservableBoolean moviesLoading = new ObservableBoolean(false);

    public final ObservableBoolean emptyViewShowing = new ObservableBoolean(false);

    public final ObservableBoolean errorViewShowing = new ObservableBoolean(false);

    private final MoviesRepository mMoviesRepository;
    private final Context mContext;

    public MoviesViewModel(@NonNull Context context, @NonNull MoviesRepository moviesRepository) {
        mContext = context.getApplicationContext();
        mMoviesRepository = moviesRepository;
    }

    public void discoverMovies(boolean showLoading) {
        // reset the states to initial states
        moviesLoading.set(showLoading);
        errorViewShowing.set(false);
        emptyViewShowing.set(false);

        mMoviesRepository.getPopularMovies(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<List<Movie>>() {
                    @Override
                    public void onNext(List<Movie> value) {
                        // show or hide empty view
                        boolean isEmpty = value == null || value.isEmpty();

                        if (!isEmpty) {
                            movies.clear();
                            movies.addAll(value);
                        }
                        emptyViewShowing.set(isEmpty);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorViewShowing.set(true);
                        moviesLoading.set(false);
                        emptyViewShowing.set(false);
                    }

                    @Override
                    public void onComplete() {
                        moviesLoading.set(false);
                        errorViewShowing.set(false);
                    }
                });
    }
}
