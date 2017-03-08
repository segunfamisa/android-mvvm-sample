package com.segunfamisa.sample.mvvm.data.remote.responses;

import com.google.gson.annotations.SerializedName;
import com.segunfamisa.sample.mvvm.data.model.Movie;

import java.util.List;

public class DiscoverMoviesResponse {

    @SerializedName("page")
    private long page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("total_results")
    private long totalResults;

    public long getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }
}
