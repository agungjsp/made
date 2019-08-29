package com.kanaldigital.dev.moviecatalogue.list.tvshow;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kanaldigital.dev.moviecatalogue.R;
import com.kanaldigital.dev.moviecatalogue.detail.tvshow.DetailTvShowActivity;
import com.kanaldigital.dev.moviecatalogue.model.TvShowData;
import com.kanaldigital.dev.moviecatalogue.rest.ApiClient;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private TvShowAdapter tvShowAdapter;
    private RecyclerView rvMovies;
    private ArrayList<TvShowData> tvShowData = new ArrayList<>();
    private TvShowViewModel tvShowViewModel;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        apiInterface = ApiClient.retrofit.create(ApiInterface.class);
        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.setListTvShow(apiInterface);
        tvShowViewModel.getTvShow().observe(this, getTvShow);

        rvMovies = view.findViewById(R.id.rv_list_tvshow);
        rvMovies.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressBarTvShow);

        tvShowAdapter = new TvShowAdapter(getActivity());
        rvMovies.setAdapter(tvShowAdapter);

        tvShowAdapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(String tvShowID) {
                Intent detailActivity = new Intent(getActivity(), DetailTvShowActivity.class);
                detailActivity.putExtra(DetailTvShowActivity.EXTRA_DETAIL_ID, tvShowID);
                startActivity(detailActivity);
            }
        });

        showRecyclerList();

        return view;
    }

    private Observer<List<TvShowData>> getTvShow = new Observer<List<TvShowData>>() {
        @Override
        public void onChanged(@Nullable List<TvShowData> tvShowData) {
            ArrayList<TvShowData> arrayTvShow = new ArrayList<>();
            arrayTvShow.addAll(tvShowData);
            tvShowAdapter.setMovies_tvshow(arrayTvShow);
            progressBar.setVisibility(View.INVISIBLE);
            tvShowAdapter.notifyDataSetChanged();
        }
    };

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
