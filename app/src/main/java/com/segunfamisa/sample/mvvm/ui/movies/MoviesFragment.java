package com.segunfamisa.sample.mvvm.ui.movies;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * A fragment to list the movies
 */
public class MoviesFragment extends BaseFragment implements MovieAdapter.ItemClickListener {


    private static final String TAG = MoviesFragment.class.getName();
    private MovieListBinding binding;

    private MovieAdapter mAdapter;
    private MoviesViewModel mMoviesViewModel;

    public static Fragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViewModel();

        setupRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();

        mMoviesViewModel.start();
    }

    private void setupViewModel() {
        mMoviesViewModel = new MoviesViewModel(getContext(), new
                MoviesRemoteRepository(MovieApiService.Creator.create()));
        binding.setMoviesViewModel(mMoviesViewModel);
    }

    private void setupRecyclerView() {
        mAdapter = new MovieAdapter(this);

        binding.movies.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.movies.setAdapter(mAdapter);
    }

    @Override
    public void onMovieClicked(Movie movie, int position) {
        Toast.makeText(getContext(), movie.toString(), Toast.LENGTH_SHORT).show();
    }
}
