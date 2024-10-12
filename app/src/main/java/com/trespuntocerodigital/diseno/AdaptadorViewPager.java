package com.trespuntocerodigital.diseno;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class AdaptadorViewPager extends FragmentStatePagerAdapter {

    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    private final List<Fragment> fragmentList;

    public AdaptadorViewPager(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return fragmentList.get(position).toString();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
