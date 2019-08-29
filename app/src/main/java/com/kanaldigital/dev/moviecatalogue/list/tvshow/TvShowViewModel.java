package com.kanaldigital.dev.moviecatalogue.list.tvshow;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kanaldigital.dev.moviecatalogue.model.TvShowData;
import com.kanaldigital.dev.moviecatalogue.model.TvShowResponse;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<List<TvShowData>> listTvShow = new MutableLiveData<>();

    void setListTvShow(ApiInterface apiInterface) {
        Call<TvShowResponse> call = apiInterface.getTvShowData();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                listTvShow.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

            }
        });
    }

    LiveData<List<TvShowData>> getTvShow() {
        return listTvShow;
    }
}
