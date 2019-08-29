package com.kanaldigital.dev.moviecatalogue.detail.tvshow;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kanaldigital.dev.moviecatalogue.model.TvShowData;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTvShowViewModel extends ViewModel {
    private MutableLiveData<TvShowData> detailTvShow = new MutableLiveData<>();

    void setDetailTvShow(ApiInterface apiInterface, String id) {
        Call<TvShowData> call = apiInterface.getDetailTvShowData(id);
        call.enqueue(new Callback<TvShowData>() {
            @Override
            public void onResponse(Call<TvShowData> call, Response<TvShowData> response) {
                detailTvShow.postValue(response.body());
            }

            @Override
            public void onFailure(Call<TvShowData> call, Throwable t) {

            }
        });
    }

    LiveData<TvShowData> getTvShow() {
        return  detailTvShow;
    }
}
