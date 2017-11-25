package com.segunfamisa.sample.mvvm.ui.movies;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.utils.ErrorResolver;
import com.segunfamisa.sample.mvvm.utils.SchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * View model for  the movies list
 */
public class MoviesListViewModel extends ViewModel {

    private final MoviesRepository moviesRepository;
    private final SchedulerProvider schedulerProvider;
    private final ErrorResolver errorResolver;
    private final CompositeDisposable disposables;

    private final MutableLiveData<List<Movie>> moviesLiveData;
    private final MutableLiveData<Boolean> loadingLiveData;
    private final MutableLiveData<String> errorLiveData;
    private final MutableLiveData<Boolean> emptyLiveData;

    public MoviesListViewModel(MoviesRepository moviesRepository,
                               SchedulerProvider schedulerProvider,
                               ErrorResolver errorResolver) {
        this.moviesRepository = moviesRepository;
        this.schedulerProvider = schedulerProvider;
        this.errorResolver = errorResolver;
        disposables = new CompositeDisposable();

        moviesLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
        emptyLiveData = new MutableLiveData<>();
    }

    // observable fields

    public MutableLiveData<List<Movie>> movies() {
        return moviesLiveData;
    }

    public MutableLiveData<Boolean> loading() {
        return loadingLiveData;
    }

    public MutableLiveData<String> error() {
        return errorLiveData;
    }

    public MutableLiveData<Boolean> empty() {
        return emptyLiveData;
    }

    //

    public void fetchMovies(final int page) {
        disposables.add(moviesRepository.getPopularMovies(page)
                .subscribeOn(schedulerProvider.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadingLiveData.postValue(page == 1);
                        emptyLiveData.postValue(false);
                        errorLiveData.postValue(null);
                    }
                })
                .subscribeWith(new DisposableObserver<List<Movie>>() {
                    @Override
                    public void onNext(List<Movie> movies) {
                        emptyLiveData.postValue(movies.size() == 0);
                        moviesLiveData.postValue(movies);
                    }

                    @Override
                    public void onError(Throwable error) {
                        loadingLiveData.postValue(false);
                        errorLiveData.postValue(errorResolver.getErrorMessage(error));
                    }

                    @Override
                    public void onComplete() {
                        loadingLiveData.postValue(false);
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
