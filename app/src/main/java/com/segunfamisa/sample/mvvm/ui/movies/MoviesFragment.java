package com.segunfamisa.sample.mvvm.ui.movies;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.remote.api.MovieApiService;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRemoteRepository;
import com.segunfamisa.sample.mvvm.databinding.MovieListBinding;
import com.segunfamisa.sample.mvvm.ui.base.BaseFragment;
import com.segunfamisa.sample.mvvm.ui.moviedetails.MovieDetailsFragment;
import com.segunfamisa.sample.mvvm.utils.DefaultSchedulerProvider;
import com.segunfamisa.sample.mvvm.utils.ErrorResolver;
import com.segunfamisa.sample.mvvm.utils.SchedulerProvider;
import com.segunfamisa.sample.mvvm.utils.ViewModelFactory;

import java.util.List;

/**
 * A fragment to list the movies
 */
public class MoviesFragment extends BaseFragment implements Interactor {


    private static final String TAG = MoviesFragment.class.getName();
    private MovieListBinding binding;

    private MovieAdapter mAdapter;
    private MoviesRemoteRepository mMoviesRepository;

    private MoviesListViewModel mViewModel;
    private ErrorResolver mErrorResolver;
    private final SchedulerProvider mSchedulerProvider = new DefaultSchedulerProvider();

    public static Fragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViewModels();
        setupToolbar();
        setupRecyclerView();

        mViewModel.fetchMovies(1);

        bindViewModel();
    }

    private void setupToolbar() {
        binding.toolbar.setTitle(R.string.app_name);
    }

    private void setupViewModels() {
        mMoviesRepository = new
                MoviesRemoteRepository(MovieApiService.Creator.create());

        mErrorResolver = new MoviesErrorResolver(getContext());

        ViewModelFactory viewModelFactory = new ViewModelFactory(mMoviesRepository,
                mSchedulerProvider, mErrorResolver);
        mViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MoviesListViewModel.class);
    }

    private void bindViewModel() {
        mViewModel.movies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies == null) return;
                mAdapter.setMovies(movies);
            }
        });

        mViewModel.error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                boolean hasError = error != null;
                binding.layoutError.setVisibility(hasError ? View.VISIBLE : View.GONE);
                if (hasError) {
                    binding.textError.setText(error);
                }
            }
        });

        mViewModel.empty().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean empty) {
                boolean isEmpty = empty == null ? false : empty;
                binding.layoutEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
            }
        });

        mViewModel.loading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loading) {
                boolean isLoading = loading == null ? false : loading;
                binding.progressLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new MovieAdapter(mMoviesRepository, this);

        binding.movies.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.movies.setAdapter(mAdapter);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        Toast.makeText(getContext(), movie.toString(), Toast.LENGTH_SHORT).show();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, MovieDetailsFragment.newInstance(movie.getId()));
        ft.addToBackStack(null);
        ft.commit();
    }
}
