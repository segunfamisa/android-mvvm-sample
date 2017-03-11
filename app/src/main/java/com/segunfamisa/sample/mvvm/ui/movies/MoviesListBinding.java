package com.segunfamisa.sample.mvvm.ui.movies;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.segunfamisa.sample.mvvm.data.model.Movie;

import java.util.List;

public class MoviesListBinding {

    @BindingAdapter("bind:movies")
    public static void setMovies(RecyclerView recyclerView, List<Movie> movies) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (adapter != null && adapter instanceof MovieAdapter) {
            ((MovieAdapter)adapter).setMovies(movies);
        } else {
            throw new IllegalStateException("RecyclerView either has no adapter set or the " +
                    "adapter isn't of type MovieAdapter");
        }
    }
}
