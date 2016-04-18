package com.example.retrofit.api;

import com.example.retrofit.model.Questions;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by NAGARAJ on 4/18/2016.
 */
public interface StackOverflowAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<Questions> downloadQuestions();
}
