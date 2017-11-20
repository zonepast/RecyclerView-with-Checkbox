package com.pariportal.seconddemo.api;

import com.pariportal.seconddemo.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android System on 11/17/2017.
 */

public interface ApiInterface {

    @GET("sources")
    Call<DataModel> getNewsData(@Query("apiKey") String apiKey);
}
