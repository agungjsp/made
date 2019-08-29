package com.kanaldigital.dev.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    private List<TvShowData> results;

    public List<TvShowData> getResults() {
        return results;
    }

    public void setResults(List<TvShowData> results) {
        this.results = results;
    }
}
