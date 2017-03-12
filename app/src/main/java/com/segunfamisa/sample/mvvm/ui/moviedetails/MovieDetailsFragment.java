package com.segunfamisa.sample.mvvm.ui.moviedetails;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.data.remote.api.MovieApiService;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRemoteRepository;
import com.segunfamisa.sample.mvvm.databinding.MovieDetailsBinding;
import com.segunfamisa.sample.mvvm.ui.base.BaseFragment;

/**
 * Fragment to show movie details
 */
public class MovieDetailsFragment extends BaseFragment {
    private static final String ARG_MOVIE_ID = "MovieDetailsFragment.MOVIE_ID";

    private MovieDetailsBinding mBinding;
    private long mMovieId;
    private MovieDetailsViewModel mViewModel;

    /**
     * Convenience method for creating an instance of the {@link MovieDetailsFragment}
     *
     * @param movieId - movie id
     * @return the instance of the fragment
     */
    public static Fragment newInstance(long movieId) {
        Fragment frag = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_MOVIE_ID, movieId);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        retrieveMovieId(savedInstanceState);

        setupToolbar();

        setupViewModel();
    }

    private void setupToolbar() {
        mBinding.toolbar.setNavigationIcon(R.drawable.nav_back);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void setupViewModel() {
        mViewModel = new MovieDetailsViewModel(new
                MoviesRemoteRepository(MovieApiService.Creator.create()));
        mBinding.setViewModel(mViewModel);
        mViewModel.getMovieDetails(mMovieId);
    }

    private void retrieveMovieId(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mMovieId = getArguments() != null ? getArguments().getLong(ARG_MOVIE_ID, -1) : -1;
        } else {
            mMovieId = savedInstanceState.getLong(ARG_MOVIE_ID, -1);
        }
        if (mMovieId == -1) {
            throw new IllegalArgumentException("You either passed a wrong value of movie id,"
                    + " or you did not use the newInstance convenience method");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(ARG_MOVIE_ID, mMovieId);
        super.onSaveInstanceState(outState);
    }
}
