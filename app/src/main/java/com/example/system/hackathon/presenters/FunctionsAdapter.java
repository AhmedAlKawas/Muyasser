package com.example.system.hackathon.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.system.hackathon.Views.activities.UserHomeActivity;
import com.example.system.hackathon.Views.activities.fragments.FatwaFragment;
import com.example.system.hackathon.Views.activities.fragments.FeedsFragment;
import com.example.system.hackathon.Views.activities.fragments.HealthConsultingFragment;
import com.example.system.hackathon.Views.activities.fragments.SettingsFragment;
import com.example.system.hackathon.Views.activities.fragments.TranslationFragment;

public class FunctionsAdapter extends FragmentPagerAdapter{

    public FunctionsAdapter(FragmentManager fm, UserHomeActivity userHomeActivity) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FeedsFragment();
            case 1:
                return new FatwaFragment();
            case 2:
                return new TranslationFragment();
            case 3:
                return new HealthConsultingFragment();
            case 4:
                return new SettingsFragment();
        }
        return null;
    }



    @Override
    public int getCount() {
        return 5;
    }
}