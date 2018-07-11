package com.leothos.netapp.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.leothos.netapp.Controllers.Fragments.DetailFragment;
import com.leothos.netapp.R;

public class DetailActivity extends AppCompatActivity {

    //VARS
    public static final String POSITION = "POSITION";
    private DetailFragment detailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Some configurations
        configureActionBar();
        configureAndShowDetailFragment();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureAndShowDetailFragment() {

        detailFragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_detail_frame_layout);

        if (detailFragment == null) {
            detailFragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_detail_frame_layout, detailFragment)
                    .commit();
        }

    }

    //Action bar with arrow to go back to mainActivity (please check Manifest)
    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}