package com.parallaxstudios.caregiver.tabs.Settings;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.parallaxstudios.caregiver.activity.DetailProfileActivity;
import com.parallaxstudios.caregiver.R;
import com.parallaxstudios.caregiver.adapter.SettingsAndaAdapter;
import com.parallaxstudios.caregiver.model.SettingsAnda;
import com.parallaxstudios.caregiver.tabs.tabsUtils.SlidingTabLayout;
import com.parallaxstudios.caregiver.utill.json.JsonParser;
import com.parallaxstudios.caregiver.utill.utill.ItemClickSupport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Settings_Anda_Fragment extends Fragment {
	
	String urlPost;
    JSONObject jsonObjectDesignPosts;
    JSONArray jsonArrayDesignContent;
    ArrayList<SettingsAnda> designs;
    SwipeRefreshLayout swipeRefreshLayout;
    String[] designTitle, designExcerpt, designImage, designImageFull, designContent;
    int postNumber = 99;
    Boolean error = false;
    RecyclerView recyclerView;
    @SuppressWarnings("rawtypes")
	RecyclerView.Adapter recyclerViewAdapter;
    View view;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    TypedValue typedValueToolbarHeight = new TypedValue();
    SlidingTabLayout tabs;
    int recyclerViewPaddingTop;
	
	public Settings_Anda_Fragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        view = inflater.inflate(R.layout.fragment_settings_profile, container, false);
        
        // Get shared preferences
        sharedPreferences = getActivity().getSharedPreferences("VALUES", Context.MODE_PRIVATE);

        // Setup RecyclerView News
        recyclerViewDesign(view);

        // Setup swipe to refresh
        swipeToRefresh(view);
        
        return view;
    }
	
	private void recyclerViewDesign(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDesign);
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);

        // Divider
        //recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(android.R.drawable.divider_horizontal_bright)));

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        urlPost = "http://wordpressdesarrolladorandroid.hol.es/category/diseno/empezar/?json=1";

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        new AsyncTaskNewsParseJson().execute(urlPost);

        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerView);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                sharedPreferences.edit().putString("TITLE", designTitle[position]).apply();
                sharedPreferences.edit().putString("CONTENT", designContent[position]).apply();
                sharedPreferences.edit().putString("EXCERPT", designExcerpt[position]).apply();
                sharedPreferences.edit().putString("IMAGE", designImageFull[position]).apply();
                Intent intent = new Intent(getActivity(), DetailProfileActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            }
        });

    }
	
	private void swipeToRefresh(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        int start = recyclerViewPaddingTop - convertToPx(48), end = recyclerViewPaddingTop + convertToPx(16);
        swipeRefreshLayout.setProgressViewOffset(true, start, end);
        TypedValue typedValueColorPrimary = new TypedValue();
        TypedValue typedValueColorAccent = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValueColorPrimary, true);
        getActivity().getTheme().resolveAttribute(R.attr.colorAccent, typedValueColorAccent, true);
        final int colorPrimary = typedValueColorPrimary.data, colorAccent = typedValueColorAccent.data;
        swipeRefreshLayout.setColorSchemeColors(colorPrimary, colorAccent);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            	new AsyncTaskNewsParseJson().execute(urlPost);
            }
        });
    }
	
	public class AsyncTaskNewsParseJson extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
        }

        // get JSON Object
        @Override
        protected String doInBackground(String... url) {

            urlPost = url[0];
            try {
                jsonObjectDesignPosts = JsonParser.readJsonFromUrl(urlPost);
                postNumber = jsonObjectDesignPosts.getJSONArray("posts").length();
                jsonArrayDesignContent = jsonObjectDesignPosts.getJSONArray("posts");
                sharedPreferences.edit().putString("DESIGN", jsonArrayDesignContent.toString()).apply();
                designTitle = new String[postNumber];
                designExcerpt = new String[postNumber];
                designContent = new String[postNumber];
                designImage = new String[postNumber];
                designImageFull = new String[postNumber];
                for (int i = 0; i < postNumber; i++) {
                    designTitle[i] = Html.fromHtml(jsonObjectDesignPosts.getJSONArray("posts").getJSONObject(i).getString("title")).toString();
                    designExcerpt[i] = Html.fromHtml(jsonObjectDesignPosts.getJSONArray("posts").getJSONObject(i).getString("excerpt")).toString();
                    designContent[i] = jsonObjectDesignPosts.getJSONArray("posts").getJSONObject(i).getString("content");
                    designImage[i] = Html.fromHtml(jsonObjectDesignPosts.getJSONArray("posts").getJSONObject(i).getJSONObject("thumbnail_images").getJSONObject("thumbnail").getString("url")).toString();
                    designImageFull[i] = Html.fromHtml(jsonObjectDesignPosts.getJSONArray("posts").getJSONObject(i).getJSONObject("thumbnail_images").getJSONObject("full").getString("url")).toString();
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                designTitle = new String[0];
                error = true;
            }
            return null;
        }

        // Set facebook items to the textviews and imageviews
        @Override
        protected void onPostExecute(String result) {

            designs = new ArrayList<>();

            //Data set used by the recyclerViewAdapter. This data will be displayed.
            if (designTitle.length != 0) {
                for (int i = postNumber - 1; i >= 0; i--) {
                    designs.add(new SettingsAnda(designTitle[i], designExcerpt[i], designImage[i]));
                }
            }
            if (error) {
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
            // Create the recyclerViewAdapter
            recyclerViewAdapter = new SettingsAndaAdapter(getActivity(), designs);
            recyclerView.setAdapter(recyclerViewAdapter);

            swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
            swipeRefreshLayout.setRefreshing(false);

            // Create the recyclerViewAdapter
            recyclerViewAdapter = new SettingsAndaAdapter(getActivity(), designs);
            recyclerView.setAdapter(recyclerViewAdapter);

            swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
            swipeRefreshLayout.setRefreshing(false);

            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
        }
    }
	
	public int convertToPx(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }
}
