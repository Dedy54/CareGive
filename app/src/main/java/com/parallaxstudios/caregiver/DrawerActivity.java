package com.parallaxstudios.caregiver;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.RecyclerViewCacheUtil;

@SuppressLint("NewApi")
public class DrawerActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean doubleBackToExitPressedOnce = false;
    @SuppressWarnings("unused")
	private int mCurrentSelectedPosition;
    @SuppressWarnings("unused")
	private final static String STATE_SELECTED_POSITION = "position";

    @SuppressWarnings("rawtypes")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_dark_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460").withIdentifier(100);
        final IProfile profile2 = new ProfileDrawerItem().withName("Bernat Borras").withEmail("alorma@github.com").withIcon(Uri.parse("https://avatars3.githubusercontent.com/u/887462?v=3&s=460")).withIdentifier(101);
        final IProfile profile3 = new ProfileDrawerItem().withName("Max Muster").withEmail("max.mustermann@gmail.com").withIcon(R.drawable.profile2).withIdentifier(102);
        final IProfile profile4 = new ProfileDrawerItem().withName("Felix House").withEmail("felix.house@gmail.com").withIcon(R.drawable.profile3).withIdentifier(103);
        final IProfile profile5 = new ProfileDrawerItem().withName("Mr. X").withEmail("mister.x.super@gmail.com").withIcon(R.drawable.profile4).withIdentifier(104);
        final IProfile profile6 = new ProfileDrawerItem().withName("Batman").withEmail("batman@gmail.com").withIcon(R.drawable.profile5).withIdentifier(105);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        profile2,
                        profile3,
                        profile4,
                        profile5,
                        profile6,
                        
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        
                    	if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            int count = 100 + headerResult.getProfiles().size() + 1;
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman" + count).withEmail("batman" + count + "@gmail.com").withIcon(R.drawable.profile5).withIdentifier(count);
                            if (headerResult.getProfiles() != null) {
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.beranda).withIcon(R.drawable.ic_home).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.Catatan_Kesehatan).withIcon(R.drawable.ic_home).withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.Notifikasi).withIcon(R.drawable.ic_home).withIdentifier(3).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.Catatan).withIcon(R.drawable.ic_home).withIdentifier(4).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.Informasi).withIcon(R.drawable.ic_home).withIdentifier(5).withSelectable(false),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.setting).withIcon(R.drawable.ic_home).withIdentifier(6).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.logout).withIcon(R.drawable.ic_home).withIdentifier(7).withSelectable(false)
                ) .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        
                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 1) {
                            	mCurrentSelectedPosition=1;
                            	result.setSelection(1, false);
                            	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new BerandaFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 2) {
                            	mCurrentSelectedPosition=2;
                            	result.setSelection(2, false);
                            	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Riwayat_KesehatanFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 3) {
                            	mCurrentSelectedPosition=3;
                            	result.setSelection(3, false);
                            	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new NotifikasiFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 4) {
                            	mCurrentSelectedPosition=4;
                            	result.setSelection(4, false);
                            	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new CatatanFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 5) {
                            	mCurrentSelectedPosition=5;
                            	result.setSelection(5, false);
                            	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new InformasiFragment()).commit();
                            } else if (drawerItem.getIdentifier() == 6) {
                            	mCurrentSelectedPosition=6;
                            	result.setSelection(6, false);
                            	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new SettingsFragment()).commit();
                            }else if (drawerItem.getIdentifier() == 7) {
                            	result.closeDrawer();
                            	finish();
                            	//getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new SettingsFragment()).commit();
                            }else {
                            	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new BerandaFragment()).commit();
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

        //if you have many different types of DrawerItems you can magically pre-cache those items to get a better scroll performance
        //make sure to init the cache after the DrawerBuilder was created as this will first clear the cache to make sure no old elements are in
        RecyclerViewCacheUtil.getInstance().withCacheSize(2).init(result);

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
        	result.setSelection(1, false);

        	getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new BerandaFragment()).commit();
        	
            //set the active profile
            headerResult.setActiveProfile(profile3);
        }

        result.updateBadge(4, new StringHolder(10 + ""));
    }

    @SuppressWarnings("unused")
	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @SuppressWarnings("rawtypes")
		@Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
    	if (doubleBackToExitPressedOnce) {
	        super.onBackPressed();
	        return;
	    }
	    this.doubleBackToExitPressedOnce = true;
	    Toast.makeText(this, "Press two twice", Toast.LENGTH_SHORT).show();
	    new Handler().postDelayed(new Runnable() {

	        @Override
	        public void run() {
	            doubleBackToExitPressedOnce=false;
	            result.closeDrawer();
	        }
	    }, 2000);
    }

}
