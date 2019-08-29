package com.kanaldigital.dev.moviecatalogue.list.movies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kanaldigital.dev.moviecatalogue.model.MovieData;
import com.kanaldigital.dev.moviecatalogue.model.MovieResponse;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<MovieData>> listMovies = new MutableLiveData<>();

    void setListMovies(ApiInterface apiInterface) {
        Call<MovieResponse> call = apiInterface.getMoviesData();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                listMovies.postValue(response.body().getResults());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    LiveData<List<MovieData>> getMovies() {
        return listMovies;
    }
}
