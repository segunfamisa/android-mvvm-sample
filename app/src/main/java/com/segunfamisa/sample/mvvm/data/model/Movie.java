package com.segunfamisa.sample.mvvm.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_count")
    private long voteCount;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("homepage")
    private String homepageUrl;

    @SerializedName("runtime")
    private long runtime;

    @SerializedName("budget")
    private long budget;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("status")
    private String status;

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342" + posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public long getRuntime() {
        return runtime;
    }

    public long getBudget() {
        return budget;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public String getTagline() {
        return tagline;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getTitle();

    }
}
