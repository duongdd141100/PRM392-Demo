package com.example.myapplication.webservice;

import com.example.myapplication.entity.PostInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiEndPoint {
    @GET("posts")
    Call<List<PostInfo>> getPostInfos();

    @GET("Posts/{id}")
    Call<PostInfo> getPostInfoById(@Path("id") String id);
}
