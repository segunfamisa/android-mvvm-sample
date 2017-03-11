package com.segunfamisa.sample.mvvm.ui.movies;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.data.repository.MoviesRepository;
import com.segunfamisa.sample.mvvm.databinding.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    private Interactor interactor;
    private MoviesRepository moviesRepository;

    public MovieAdapter(MoviesRepository moviesRepository, Interactor interactor) {
        this.interactor = interactor;
        this.moviesRepository = moviesRepository;
        movies = new ArrayList<>();
    }

    public void setMovies(@NonNull List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItem movieItem = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_layout, parent, false);
        return new MovieViewHolder(movieItem);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        final MovieItemViewModel movieItemViewModel = new MovieItemViewModel(moviesRepository,
                interactor);
        movieItemViewModel.setMovie(movie);
        holder.setMovieItemViewModel(movieItemViewModel);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
