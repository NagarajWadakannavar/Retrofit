package com.example.retrofit.api;

import com.example.retrofit.model.Questions;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by NAGARAJ on 4/18/2016.
 */
public interface StackOverflowAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<Questions> downloadQuestions();

    @GET
    Call<Questions> downloadQuestions(@Url String url);

    @GET("/2.2/{type}?order=desc&sort=creation")
    Call<Questions> downloadQuestions(@Path("type") String user,@Query("site") String query);

    @GET("/2.2/questions")
    Call<Questions> downloadQuestions(@QueryMap Map<String, String> options);


}
