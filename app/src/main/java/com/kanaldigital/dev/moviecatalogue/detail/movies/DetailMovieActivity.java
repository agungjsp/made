package com.kanaldigital.dev.moviecatalogue.detail.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.kanaldigital.dev.moviecatalogue.R;
import com.kanaldigital.dev.moviecatalogue.model.MovieData;
import com.kanaldigital.dev.moviecatalogue.rest.ApiClient;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ID = "extra_movie_id";
    private DetailMovieViewModel detailMovieViewModel;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    ImageView bgBlur, imgPhoto, imdbLogo;
    TextView tvDesc, tvRelease, tvDuration, tvImdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFragment);
        setContentView(R.layout.activity_detail_movie);

        apiInterface = ApiClient.retrofit.create(ApiInterface.class);
        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        detailMovieViewModel.setDetailMovies(apiInterface, getIntent().getStringExtra(EXTRA_MOVIE_ID));
        detailMovieViewModel.getMovies().observe(this, getMovies);

        bgBlur = findViewById(R.id.bg_blur);
        imgPhoto = findViewById(R.id.img_detail_photo);
        imdbLogo = findViewById(R.id.imdb_logo);
        tvDesc = findViewById(R.id.tv_detail_desc);
        tvRelease = findViewById(R.id.tv_detail_release);
        tvDuration = findViewById(R.id.tv_detail_duration);
        tvImdb = findViewById(R.id.tv_detail_imdb);
        progressBar = findViewById(R.id.progressBarDetailMovie);

    }

    private Observer<MovieData> getMovies = new Observer<MovieData>() {
        @Override
        public void onChanged(@Nullable MovieData movieData) {
            String bgBlurs = movieData.getPhoto();
            String imgPhotos = movieData.getPhoto();
            String tvName = movieData.getName();
            String tvDescs = movieData.getDesc();
            String tvReleases = movieData.getRelease();
            String tvDurations = movieData.getDuration();
            String tvImdbs = movieData.getImdb();

            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/w500/" + bgBlurs)
                    .apply(bitmapTransform(new BlurTransformation(25, 1)))
                    .into(bgBlur);
            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/w500/" + imgPhotos)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imgPhoto);
            Glide.with(getApplicationContext())
                    .load(R.drawable.imdb_logo)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imdbLogo);

            tvDesc.setText(tvDescs);
            tvRelease.setText(tvReleases);
            tvDuration.setText(tvDurations);
            tvImdb.setText(tvImdbs);

            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setTitle(tvName);
                ab.setDisplayHomeAsUpEnabled(true);
            }

            progressBar.setVisibility(View.INVISIBLE);

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
