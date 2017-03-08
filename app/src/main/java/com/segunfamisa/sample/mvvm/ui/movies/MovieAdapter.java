package com.segunfamisa.sample.mvvm.ui.movies;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.segunfamisa.sample.mvvm.R;
import com.segunfamisa.sample.mvvm.data.model.Movie;
import com.segunfamisa.sample.mvvm.databinding.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context context;
    private List<Movie> movies;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onMovieClicked(Movie movie, int position);
    }

    public MovieAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
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
        holder.setMovie(movie);

        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onMovieClicked(movie, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
