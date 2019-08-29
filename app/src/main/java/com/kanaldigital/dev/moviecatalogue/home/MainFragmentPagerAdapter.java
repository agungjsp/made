package com.kanaldigital.dev.moviecatalogue.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragment = new ArrayList<>();
    private final List<String> mTitleFragment = new ArrayList<>();

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragment.add(fragment);
        mTitleFragment.add(title);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return mTitleFragment.get(i);
    }
}
