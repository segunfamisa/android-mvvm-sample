package com.segunfamisa.sample.mvvm.ui.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.databinding.MovieItem;
import com.segunfamisa.sample.mvvm.utils.ImageResolver;

/**
 * Movie item view holder
 */
public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    private final MovieItem movieItem;
    private final MovieItemClickListener movieItemClickListener;

    public MovieItemViewHolder(MovieItem movieItem, MovieItemClickListener movieItemClickListener) {
        super(movieItem.getRoot());
        this.movieItem = movieItem;
        this.movieItemClickListener = movieItemClickListener;
    }

    public void bind(final Movie movie) {
        Glide.with(movieItem.getRoot().getContext())
                .load(ImageResolver.getPosterUrl(movie))
                .into(movieItem.poster);

        movieItem.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieItemClickListener.showMovieDetails(movie);
            }
        });
    }
}
