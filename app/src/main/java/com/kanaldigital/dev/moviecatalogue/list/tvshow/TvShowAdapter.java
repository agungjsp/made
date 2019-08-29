package com.kanaldigital.dev.moviecatalogue.list.tvshow;

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
import com.kanaldigital.dev.moviecatalogue.model.TvShowData;
import com.kanaldigital.dev.moviecatalogue.R;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ListViewHolder> {

    Context context;

    private ArrayList<TvShowData> listMovie_tvshow;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setMovies_tvshow(ArrayList<TvShowData> movies_tvshow) {
        this.listMovie_tvshow = movies_tvshow;
    }

    public TvShowAdapter(Context context) {
        this.context = context;
        listMovie_tvshow = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tvshow, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
        final TvShowData movie_tvshow = listMovie_tvshow.get(i);

        Glide.with(listViewHolder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + movie_tvshow.getPhoto())
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(listViewHolder.imgPhoto);

        listViewHolder.tvName.setText(movie_tvshow.getName());
        listViewHolder.tvRelease.setText(movie_tvshow.getRelease());
        listViewHolder.tvDuration.setText(movie_tvshow.getDuration());
        listViewHolder.tvImdb.setText(movie_tvshow.getImdb());

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(movie_tvshow.getId());
            }
        });

    }

    public interface OnItemClickCallback {
        void onItemClicked(String tvShowID);
    }

    @Override
    public int getItemCount() {
        return listMovie_tvshow.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvRelease, tvDuration, tvRating, tvGenre, tvImdb;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo_tvshow);
            tvName = itemView.findViewById(R.id.tv_title_tvshow);
            tvRelease = itemView.findViewById(R.id.tv_released_tvshow);
            tvDuration = itemView.findViewById(R.id.tv_duration_tvshow);
            tvRating = itemView.findViewById(R.id.tv_rating_tvshow);
            tvGenre = itemView.findViewById(R.id.tv_genre_tvshow);
            tvImdb = itemView.findViewById(R.id.tv_imdb_tvshow);

        }
    }
}
