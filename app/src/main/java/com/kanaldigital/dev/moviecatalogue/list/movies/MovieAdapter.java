package com.kanaldigital.dev.moviecatalogue.list.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.kanaldigital.dev.moviecatalogue.model.MovieData;
import com.kanaldigital.dev.moviecatalogue.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {

    Context context;

    private ArrayList<MovieData> listMovieData;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setMovies(ArrayList<MovieData> movieData) {
        this.listMovieData = movieData;
    }

    public MovieAdapter(Context context) {
        this.context = context;
        listMovieData = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
        final MovieData movieData = listMovieData.get(i);

        Glide.with(listViewHolder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + movieData.getPhoto())
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(listViewHolder.imgPhoto);

        listViewHolder.tvName.setText(movieData.getName());
        listViewHolder.tvRelease.setText(movieData.getRelease());
        listViewHolder.tvDuration.setText(movieData.getDuration());
        listViewHolder.tvImdb.setText(movieData.getImdb());

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(movieData.getId());
            }
        });

    }

    public interface OnItemClickCallback {
        void onItemClicked(String movieID);
    }

    @Override
    public int getItemCount() {
        return listMovieData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvRelease, tvDuration, tvGenre, tvImdb;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvName = itemView.findViewById(R.id.tv_title);
            tvRelease = itemView.findViewById(R.id.tv_released);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            tvGenre = itemView.findViewById(R.id.tv_genre);
            tvImdb = itemView.findViewById(R.id.tv_imdb);

        }
    }
}
