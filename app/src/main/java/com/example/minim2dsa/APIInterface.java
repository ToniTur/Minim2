package com.example.minim2dsa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<List<Repository>> getRepositories(@Path("username") String username);
}