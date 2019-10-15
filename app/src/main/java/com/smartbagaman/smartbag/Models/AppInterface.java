package com.smartbagaman.smartbag.Models;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AppInterface
{
    String BaseURL = "http://172.16.88.119/smartbag/index.php/api/";
    @FormUrlEncoded
    @POST("home/items")
    Call<JsonObject> items(
            @Field("user_id") String user_id
    );
    @FormUrlEncoded
    @POST("home/some_items")
    Call<JsonObject> check(
            @Field("user_id") String user_id
    );
}
