package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.netapp.R;

import butterknife.BindView;

import static com.openclassrooms.netapp.Controllers.Fragments.MainFragment.USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";
    @BindView(R.id.detail_user_login)
    TextView mUserLogin;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        //Retrieve the data from mainFragment
        if (getArguments().containsKey(USER) && getArguments() != null) {
            String userName = getArguments().getString(USER);
            mUserLogin.setText(userName);
        }

        Log.d(TAG, "onCreateView: "+ USER);

        return view;

    }

}
