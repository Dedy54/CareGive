package com.parallaxstudios.caregiver.adapter.Viewpager;

import com.parallaxstudios.caregiver.tabs.Settings.Settings_Anda_Fragment;
import com.parallaxstudios.caregiver.tabs.Settings.Settings_Orang_Tua_Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsSettingsViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int tabNumber;

    // Constructor
    public TabsSettingsViewPagerAdapter (FragmentManager fragmentManager, CharSequence titles[], int tabNumber) {
        super(fragmentManager);

        this.titles = titles;
        this.tabNumber = tabNumber;

    }

    // Return Fragment for each position
    @Override
    public Fragment getItem(int position) {
    	Settings_Anda_Fragment tabSettings_Anda = new Settings_Anda_Fragment();
    	Settings_Orang_Tua_Fragment tabSettings_Orangtua = new Settings_Orang_Tua_Fragment();
        switch (position) {
            case 0:
                return tabSettings_Anda;
            case 1:
                return tabSettings_Orangtua;
        }
        return null;
    }

    // Return tab titles for each position

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    // Return tab number.
    @Override
    public int getCount() {
        return tabNumber;
    }
}