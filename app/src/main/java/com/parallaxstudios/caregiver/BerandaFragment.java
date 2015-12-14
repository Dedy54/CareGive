package com.parallaxstudios.caregiver;

import com.parallaxstudios.caregiver.activity.DetailProfileActivity;
import com.parallaxstudios.caregiver.adapter.Viewpager.TabsBerandaViewPagerAdapter;
import com.parallaxstudios.caregiver.tabs.tabsUtils.SlidingTabLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BerandaFragment extends Fragment {

	TabsBerandaViewPagerAdapter tabsBerandaViewPagerAdapter;
	View view;
	CharSequence titles[] = {"Timeline", "Riwayat", "Info"};
	int tabNumber = titles.length;
	ViewPager pager;
	SlidingTabLayout tabs;
	TypedValue typedValueToolbarHeight = new TypedValue();
	int tabsPaddingTop;
	ImageView ivProfile;
	public BerandaFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		view = inflater.inflate(R.layout.fragment_beranda, container, false);
		
		getActivity().setTitle("Beranda");
		
		setupTabs();
		OnClickProfile(view);
		
        return view;
    }
	
	public void setupTabs() {
		tabsBerandaViewPagerAdapter = new TabsBerandaViewPagerAdapter(getFragmentManager(), titles, tabNumber);
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(tabsBerandaViewPagerAdapter);
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(false);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.md_pink_400);
            }
        });

        // Setup tabs top padding
        getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValueToolbarHeight, true);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (Build.VERSION.SDK_INT >= 19) {
                //tabsPaddingTop = TypedValue.complexToDimensionPixelSize(typedValueToolbarHeight.data, getResources().getDisplayMetrics()) + convertToPx(25);
            }else{
                //tabsPaddingTop = TypedValue.complexToDimensionPixelSize(typedValueToolbarHeight.data, getResources().getDisplayMetrics());
            }
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 21) {
                //tabsPaddingTop = TypedValue.complexToDimensionPixelSize(typedValueToolbarHeight.data, getResources().getDisplayMetrics());
            }
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21){
                //tabsPaddingTop = TypedValue.complexToDimensionPixelSize(typedValueToolbarHeight.data, getResources().getDisplayMetrics()) + convertToPx(25);
            }
            if (Build.VERSION.SDK_INT < 19) {
                //tabsPaddingTop = TypedValue.complexToDimensionPixelSize(typedValueToolbarHeight.data, getResources().getDisplayMetrics());
            }
        }
        tabs.setPadding(convertToPx(48), tabsPaddingTop, convertToPx(16), 0);

        tabs.setViewPager(pager);
    }
	
	public int convertToPx(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }
	
	@SuppressLint("DefaultLocale")
	public String FirstChar(String str) {
		String outStr = null;
		for(int i=0; i< (str.length());i++){
	        if(str.charAt(i)==' '){
	            outStr= outStr.substring(0,i+1)+str.substring(i+1,i+2).toUpperCase()+str.substring(i+2);
	        }else if(i==0){
	            outStr=str.substring(0,1).toUpperCase()+" "+str.substring(0,1).toUpperCase()+str.substring(1);
	        }
	    }
		return outStr;
	}
	
	public void OnClickProfile(View v) {
		ivProfile = (ImageView) v.findViewById(R.id.icon);
		ivProfile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openSettings();
			}
		});
	}
	
	private void openSettings() {
    	Intent intent = new Intent(getActivity(), DetailProfileActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }
}
