package com.example.codingexam.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.codingexam.api.RetrofitClient;
import com.example.codingexam.models.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationAPIService {
    public MutableLiveData<ResponseModel> getInformation(){
        final MutableLiveData<ResponseModel> responseModelMutableLiveData = new MutableLiveData<>();
        Call<ResponseModel> serviceResponseCall = RetrofitClient.getInstance().getApi().getInformation();
        serviceResponseCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.code() == 200){
                    responseModelMutableLiveData.setValue(response.body());
                } else {
                    responseModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage());
            }
        });
        return responseModelMutableLiveData;
    }
}
