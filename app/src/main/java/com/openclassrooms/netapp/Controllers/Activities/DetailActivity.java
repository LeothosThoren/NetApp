package com.openclassrooms.netapp.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.openclassrooms.netapp.Controllers.Fragments.DetailFragment;
import com.openclassrooms.netapp.R;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
}