package com.parallaxstudios.caregiver.adapter.Viewpager;

import com.parallaxstudios.caregiver.Riwayat_KesehatanFragment;
import com.parallaxstudios.caregiver.tabs.Beranda.Beranda_News_Fragment;
import com.parallaxstudios.caregiver.tabs.Beranda.Beranda_Timeline_Fragment;
import com.parallaxstudios.caregiver.tabs.Notifikasi.Notifikasi_Anda_Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsBerandaViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int tabNumber;

    // Constructor
    public TabsBerandaViewPagerAdapter (FragmentManager fragmentManager, CharSequence titles[], int tabNumber) {
        super(fragmentManager);

        this.titles = titles;
        this.tabNumber = tabNumber;

    }

    // Return Fragment for each position
    @Override
    public Fragment getItem(int position) {
    	Beranda_Timeline_Fragment tabBerandaTimeline = new Beranda_Timeline_Fragment();
    	Riwayat_KesehatanFragment tabBerandaHistory = new Riwayat_KesehatanFragment();
    	Beranda_News_Fragment tabBerandaTips = new Beranda_News_Fragment();
        switch (position) {
            case 0:
                return tabBerandaTimeline;
            case 1:
                return tabBerandaHistory;
            case 2:
                return tabBerandaTips;
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