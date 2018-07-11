package com.leothos.netapp.Utils;

import com.leothos.netapp.Models.GithubUser;
import com.leothos.netapp.Models.GithubUserInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Philippe on 20/12/2017.
 */

public interface GithubService {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("users/{username}/following")
    Observable<List<GithubUser>> getFollowing(@Path("username") String username);

    //We need this object to pull a request with retrofit and get the info
    @GET("/users/{username}")
    Observable<GithubUserInfo> getUserInfos(@Path("username") String username);
}
