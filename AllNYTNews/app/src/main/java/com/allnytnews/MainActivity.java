package com.allnytnews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.allnytnews.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.util.ApiResultCallback;
import com.util.PojoResults;
import com.util.RecyclerTouchListener;
import com.util.ResultsAdapter;
import com.util.UILApplication;
import com.util.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ApiResultCallback mCallback;
    private ArrayList<PojoResults> mListResults;
    RecyclerView mRecyclerView;
    private ResultsAdapter mAdapter;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.RecyclerView);
        mProgressBar = findViewById(R.id.progressBar1);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCallback = new ApiResultCallback() {
            @Override
            public void getResult_Callback(String a, int resultcode) {
                try {
                    JSONObject jsonObject = new JSONObject(a);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    mListResults = new ArrayList<>();
                    Gson gson = new Gson();
                    Type listType = new TypeToken<ArrayList<PojoResults>>() {
                    }.getType();

                    mListResults = gson.fromJson(jsonArray.toString(), listType);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectResult = jsonArray.getJSONObject(i);
                        JSONArray jsonArrayMedia = jsonObjectResult.getJSONArray("media");
                        JSONObject jsonObjectMedia = jsonArrayMedia.getJSONObject(0);
                        JSONArray jsonArrayMediaMetadata = jsonObjectMedia.getJSONArray("media-metadata");
                        JSONObject jsonObjectMediaMetadata = jsonArrayMediaMetadata.getJSONObject(0);
                        String s = jsonObjectMediaMetadata.getString("url");
                        mListResults.get(i).setBrandLogoURL(s);
                    }

                    ShowView(mListResults);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        VolleyUtils.GET_METHOD(MainActivity.this, mCallback,
                getResources().getString(R.string.Server) +
                        getResources().getString(R.string.getMostpopular_url) +
                        UILApplication.APIKEY);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener
                (getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                                mListResults.get(position).getUrl()));
                        startActivity(browserIntent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
    }

    private void ShowView(ArrayList<PojoResults> mListResults) {
        mAdapter = new ResultsAdapter(mListResults, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
