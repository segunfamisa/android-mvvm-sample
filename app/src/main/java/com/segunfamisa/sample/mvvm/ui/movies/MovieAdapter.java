package com.segunfamisa.sample.mvvm.ui.movies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.databinding.MovieItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Movie list adapter
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {

    private List<Movie> movies;
    private MovieItemClickListener movieItemClickListener;

    public MovieAdapter(MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener;
        movies = new ArrayList<>();
    }

    public void setMovies(@NonNull List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItem movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_layout, parent, false);
        return new MovieItemViewHolder(movieItem, movieItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MovieItemViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
