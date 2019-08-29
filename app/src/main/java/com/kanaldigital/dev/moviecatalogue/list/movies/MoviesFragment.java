package com.kanaldigital.dev.moviecatalogue.list.movies;


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
import com.kanaldigital.dev.moviecatalogue.detail.movies.DetailMovieActivity;
import com.kanaldigital.dev.moviecatalogue.model.MovieData;
import com.kanaldigital.dev.moviecatalogue.rest.ApiClient;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView rvMovies;
    private ArrayList<MovieData> movieData = new ArrayList<>();
    private MovieViewModel movieViewModel;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        apiInterface = ApiClient.retrofit.create(ApiInterface.class);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.setListMovies(apiInterface);
        movieViewModel.getMovies().observe(this, getMovies);

        rvMovies = view.findViewById(R.id.rv_listMovie);
        rvMovies.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressBarMovie);

        movieAdapter = new MovieAdapter(getActivity());
        rvMovies.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(String movieID) {
                Intent detailActivity = new Intent(getActivity(), DetailMovieActivity.class);
                detailActivity.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movieID);
                startActivity(detailActivity);
            }
        });
        showRecyclerList();

        return view;
    }

    private Observer<List<MovieData>> getMovies = new Observer<List<MovieData>>() {
        @Override
        public void onChanged(@Nullable List<MovieData> movieData) {
            ArrayList<MovieData> arrayMovie = new ArrayList<>();
            arrayMovie.addAll(movieData);
            movieAdapter.setMovies(arrayMovie);
            progressBar.setVisibility(View.INVISIBLE);
            movieAdapter.notifyDataSetChanged();
        }
    };


    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
