package com.example.codingexam.api;

import com.example.codingexam.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("v3/bd2a3c78-bb03-43a0-9c81-122137a9ebe2")
    Call<ResponseModel> getInformation();
}
