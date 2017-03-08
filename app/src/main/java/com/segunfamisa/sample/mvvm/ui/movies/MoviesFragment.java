package com.segunfamisa.sample.mvvm.ui.movies;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.ui.base.BaseFragment;

/**
 * A fragment to list the movies
 */
public class MoviesFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

}
