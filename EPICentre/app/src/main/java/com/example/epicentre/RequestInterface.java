package com.example.epicentre;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestInterface {

    @Headers("Content-Type: application/json")
    @POST("LocationAPI.php")
    public Call<ResponseJSON> SendLocation(@Body LocationJSON locationJSON);
}
