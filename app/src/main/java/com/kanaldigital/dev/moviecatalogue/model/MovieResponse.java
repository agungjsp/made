package com.kanaldigital.dev.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<MovieData> results;

    public List<MovieData> getResults() {
        return results;
    }

    public void setResults(List<MovieData> results) {
        this.results = results;
    }
}
