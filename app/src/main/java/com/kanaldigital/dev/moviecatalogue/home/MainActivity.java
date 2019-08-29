package com.kanaldigital.dev.moviecatalogue.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kanaldigital.dev.moviecatalogue.R;
import com.kanaldigital.dev.moviecatalogue.list.favorite.FavoriteFragment;
import com.kanaldigital.dev.moviecatalogue.list.movies.MoviesFragment;
import com.kanaldigital.dev.moviecatalogue.list.tvshow.TvShowFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new MoviesFragment());

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(this);

        initView();

    }

    private boolean loadFragment(MoviesFragment moviesFragment) {
        if (moviesFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, new MoviesFragment()).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment = new MoviesFragment();
                break;
            case R.id.nav_favorite:
                fragment = new FavoriteFragment();
                break;
        }
        return false;
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(final ViewPager viewPager) {
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mainFragmentPagerAdapter.addFragment(new MoviesFragment(), getString(R.string.text_movie));
        mainFragmentPagerAdapter.addFragment(new TvShowFragment(), getString(R.string.text_tvshow));
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
