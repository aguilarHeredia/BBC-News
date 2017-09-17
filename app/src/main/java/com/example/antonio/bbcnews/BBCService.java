package com.example.antonio.bbcnews;

import com.example.antonio.bbcnews.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by antonio on 14/09/2017.
 */

public interface BBCService {

    @GET("articles")
    //Call<News> obtenerArticulos(@Query("sortBy") String resourceId, @Query("apiKey") String query);
    Call<News> obtenerArticulos(@Query("source") String type, @Query("sortBy") String order, @Query("apiKey") String key);


}
