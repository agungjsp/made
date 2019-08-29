package com.kanaldigital.dev.moviecatalogue.detail.tvshow;

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
import com.kanaldigital.dev.moviecatalogue.model.TvShowData;
import com.kanaldigital.dev.moviecatalogue.rest.ApiClient;
import com.kanaldigital.dev.moviecatalogue.rest.ApiInterface;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailTvShowActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL_ID = "extra_detail_id";
    private DetailTvShowViewModel detailTvShowViewModel;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    ImageView bgBlur, imgPhoto, imdbLogo;
    TextView tvDesc, tvRelease, tvDuration, tvImdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeFragment);
        setContentView(R.layout.activity_detail_tvshow);

        apiInterface = ApiClient.retrofit.create(ApiInterface.class);
        detailTvShowViewModel = ViewModelProviders.of(this).get(DetailTvShowViewModel.class);
        detailTvShowViewModel.setDetailTvShow(apiInterface, getIntent().getStringExtra(EXTRA_DETAIL_ID));
        detailTvShowViewModel.getTvShow().observe(this, getTvShow);

        bgBlur = findViewById(R.id.bg_blur_tvshow);
        imgPhoto = findViewById(R.id.img_detail_photo_tvshow);
        imdbLogo = findViewById(R.id.imdb_logo_tvshow);
        tvDesc = findViewById(R.id.tv_detail_desc_tvshow);
        tvRelease = findViewById(R.id.tv_detail_release_tvshow);
        tvDuration = findViewById(R.id.tv_detail_duration_tvshow);
        tvImdb = findViewById(R.id.tv_detail_imdb_tvshow);
        progressBar = findViewById(R.id.progressBarDetailTvShow);

    }

    private Observer<TvShowData> getTvShow = new Observer<TvShowData>() {
        @Override
        public void onChanged(@Nullable TvShowData tvShowData) {
            String bgBlurs = tvShowData.getPhoto();
            String imgPhotos = tvShowData.getPhoto();
            String tvName = tvShowData.getName();
            String tvDescs = tvShowData.getDesc();
            String tvReleases = tvShowData.getRelease();
            String tvDurations = tvShowData.getDuration();
            String tvImdbs = tvShowData.getImdb();

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
