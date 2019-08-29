package com.kanaldigital.dev.moviecatalogue.rest;

import com.kanaldigital.dev.moviecatalogue.model.MovieData;
import com.kanaldigital.dev.moviecatalogue.model.MovieResponse;
import com.kanaldigital.dev.moviecatalogue.model.TvShowData;
import com.kanaldigital.dev.moviecatalogue.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie?api_key=68f5211b10908fe8e90edf53ade146ad")
    Call<MovieResponse> getMoviesData();

    @GET("movie/{id}?api_key=68f5211b10908fe8e90edf53ade146ad")
    Call<MovieData> getDetailMovieData(
            @Path("id") String path
    );

    @GET("discover/tv?api_key=68f5211b10908fe8e90edf53ade146ad")
    Call<TvShowResponse> getTvShowData();

    @GET("tv/{id}?api_key=68f5211b10908fe8e90edf53ade146ad")
    Call<TvShowData> getDetailTvShowData(
            @Path("id") String path
    );
}
