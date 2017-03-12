package com.segunfamisa.sample.mvvm.ui.moviedetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.ui.base.BaseFragment;
import com.segunfamisa.sample.mvvm.ui.movies.MoviesFragment;

/**
 * Fragment to show movie details
 */
public class MovieDetailsFragment extends BaseFragment {
    private static final String ARG_MOVIE_ID = "MovieDetailsFragment.MOVIE_ID";

    private String mMovieId;

    /**
     * Convenience method for creating an instance of the {@link MovieDetailsFragment}
     *
     * @param movieId - movie id
     * @return the instance of the fragment
     */
    public static Fragment newInstance(long movieId) {
        Fragment frag = new MoviesFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_MOVIE_ID, movieId);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

}
