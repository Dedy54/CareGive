package com.parallaxstudios.caregiver.adapter.Viewpager;

import com.parallaxstudios.caregiver.tabs.Notifikasi.Notifikasi_Anda_Fragment;
import com.parallaxstudios.caregiver.tabs.Notifikasi.Notifikasi_Orang_Tua_Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsNotifikasiViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int tabNumber;

    // Constructor
    public TabsNotifikasiViewPagerAdapter (FragmentManager fragmentManager, CharSequence titles[], int tabNumber) {
        super(fragmentManager);

        this.titles = titles;
        this.tabNumber = tabNumber;

    }

    // Return Fragment for each position
    @Override
    public Fragment getItem(int position) {
    	Notifikasi_Anda_Fragment tabDesignTimeline = new Notifikasi_Anda_Fragment();
    	Notifikasi_Orang_Tua_Fragment tabNotifikasi_Orangtua = new Notifikasi_Orang_Tua_Fragment();
        switch (position) {
            case 0:
                return tabNotifikasi_Orangtua;
            case 1:
                return tabDesignTimeline;
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