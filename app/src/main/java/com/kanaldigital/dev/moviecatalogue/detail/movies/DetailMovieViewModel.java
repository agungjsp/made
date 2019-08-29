package com.kanaldigital.dev.moviecatalogue.detail.movies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kanaldigital.dev.moviecatalogue.model.MovieData;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieViewModel extends ViewModel {
    private MutableLiveData<MovieData> detailMovie = new MutableLiveData<>();

    void setDetailMovies(ApiInterface apiInterface, String id) {
        Call<MovieData> call = apiInterface.getDetailMovieData(id);
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                detailMovie.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {

            }
        });
    }

    LiveData<MovieData> getMovies() {
        return detailMovie;
    }
}
