package com.segunfamisa.sample.mvvm.ui.movies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.segunfamisa.sample.mvvm.databinding.MovieItem;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private MovieItem movieItem;

    public MovieViewHolder(MovieItem movieItem) {
        super(movieItem.getRoot());
        this.movieItem = movieItem;
    }

    public void setMovieItemViewModel(@NonNull MovieItemViewModel movieItemViewModel) {
        movieItem.setMovieItemViewModel(movieItemViewModel);
        movieItem.executePendingBindings();
    }
}
