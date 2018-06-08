package com.openclassrooms.netapp.Controllers.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.netapp.Models.GithubUserInfo;
import com.openclassrooms.netapp.R;
import com.openclassrooms.netapp.Utils.GithubStreams;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.openclassrooms.netapp.Controllers.Activities.DetailActivity.POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";

    //Widget
    @BindView(R.id.detail_user_login)
    TextView mUserLogin;
    @BindView(R.id.detail_avatar)
    ImageView mAvatar;
    @BindView(R.id.detail_followings_nb)
    TextView mFollowingNb;
    @BindView(R.id.detail_followers_nb)
    TextView mFollowersNb;
    @BindView(R.id.detail_repos_nb)
    TextView mReposNb;

    //Var
    private int position;
    Disposable mDisposable;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        //Retrieve the position within the click from previous fragment
        position = getActivity().getIntent().getIntExtra(POSITION, 0);
        Log.d(TAG, "onCreateView: show position "+ position);

        //Method request
        this.executeHttpRequestWithRetrofitUserInfo();

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofitUserInfo() {
        //Note: we use the stream with Map and flatMap option with a minor modification
        this.mDisposable = GithubStreams.streamFetchUserFollowingAndFetchFirstUserInfos("JakeWharton", position)
                .subscribeWith(new DisposableObserver<GithubUserInfo>() {
                    @Override
                    public void onNext(GithubUserInfo usersInfo) {
                        //For tests
                        Log.d(TAG, "onNext followers: " + usersInfo.getFollowers());
                        Log.d(TAG, "onNext followings: " + usersInfo.getFollowing());
                        Log.d(TAG, "onNext repos: " + usersInfo.getPublicRepos());
                        Log.d(TAG, "onNext username: " + usersInfo.getLogin());
                        Log.d(TAG, "onNext url_avatar: " + usersInfo.getAvatarUrl());

                        //To prevent the app for crashing
                        try {
                            updateUI(usersInfo);
                        } catch (IllegalStateException e) {
                            Log.e(TAG, "onNext: ERROR" + e.getMessage());
                        } catch (NullPointerException e) {
                            Log.e(TAG, "onNext: ", e.fillInStackTrace());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: Throwable" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: just to say hourra!!");
                    }
                });
    }

    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    @SuppressLint("SetTextI18n")
    private void updateUI(GithubUserInfo users) {
        //Call Glide for one use, no for lists
        Glide.with(this).load(users.getAvatarUrl()).apply(RequestOptions.circleCropTransform()).into(mAvatar);
        this.mUserLogin.setText(users.getLogin());
        //Integer to cast into String, String.format will do as well
        this.mFollowingNb.setText(users.getFollowing().toString());
        this.mFollowersNb.setText(users.getFollowers().toString());
        this.mReposNb.setText(users.getPublicRepos().toString());

    }

}
