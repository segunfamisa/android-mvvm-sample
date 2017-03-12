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
        return posterPath;
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

    private Movie(String posterPath, boolean adult, String overview, String releaseDate, long id,
                  String title, String backdropPath, double popularity, long voteCount,
                  boolean video, double voteAverage, String imdbId, String homepageUrl,
                  long runtime, long budget, List<ProductionCompany> productionCompanies,
                  String tagline, String status) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
        this.imdbId = imdbId;
        this.homepageUrl = homepageUrl;
        this.runtime = runtime;
        this.budget = budget;
        this.productionCompanies = productionCompanies;
        this.tagline = tagline;
        this.status = status;
    }

    public static class Builder {
        private String posterPath;
        private boolean adult;
        private String overview;
        private String releaseDate;
        private long id;
        private String title;
        private String backdropPath;
        private double popularity;
        private long voteCount;
        private boolean video;
        private double voteAverage;
        private String imdbId;
        private String homepageUrl;
        private long runtime;
        private long budget;
        private List<ProductionCompany> productionCompanies;
        private String tagline;
        private String status;

        public Builder setPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public Builder setAdult(boolean adult) {
            this.adult = adult;
            return this;
        }

        public Builder setOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public Builder setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
            return this;
        }

        public Builder setPopularity(double popularity) {
            this.popularity = popularity;
            return this;
        }

        public Builder setVoteCount(long voteCount) {
            this.voteCount = voteCount;
            return this;
        }

        public Builder setVideo(boolean video) {
            this.video = video;
            return this;
        }

        public Builder setVoteAverage(double voteAverage) {
            this.voteAverage = voteAverage;
            return this;
        }

        public Builder setImdbId(String imdbId) {
            this.imdbId = imdbId;
            return this;
        }

        public Builder setHomepageUrl(String homepageUrl) {
            this.homepageUrl = homepageUrl;
            return this;
        }

        public Builder setRuntime(long runtime) {
            this.runtime = runtime;
            return this;
        }

        public Builder setBudget(long budget) {
            this.budget = budget;
            return this;
        }

        public Builder setProductionCompanies(List<ProductionCompany> productionCompanies) {
            this.productionCompanies = productionCompanies;
            return this;
        }

        public Builder setTagline(String tagline) {
            this.tagline = tagline;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Movie build() {
            return new Movie(posterPath, adult, overview, releaseDate, id, title, backdropPath,
                    popularity, voteCount, video, voteAverage, imdbId, homepageUrl, runtime, budget,
                    productionCompanies, tagline, status);
        }
    }
}
