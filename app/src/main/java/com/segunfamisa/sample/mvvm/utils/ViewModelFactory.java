package com.segunfamisa.sample.mvvm.utils;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.ui.movies.MoviesListViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MoviesRepository moviesRepository;
    private final SchedulerProvider schedulerProvider;
    private final ErrorResolver errorResolver;

    public ViewModelFactory(MoviesRepository moviesRepository,
                     SchedulerProvider schedulerProvider,
                     ErrorResolver errorResolver) {

        this.moviesRepository = moviesRepository;
        this.schedulerProvider = schedulerProvider;
        this.errorResolver = errorResolver;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesListViewModel.class)) {
            return (T) new MoviesListViewModel(moviesRepository, schedulerProvider, errorResolver);
        }
        throw new UnsupportedOperationException("This view model is strange, we don't know it");
    }
}
