package com.example.codingexam.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.codingexam.models.ResponseModel;
import com.example.codingexam.service.InformationAPIService;

public class MainActivityViewModel extends ViewModel {
    private InformationAPIService informationAPIService;
    private MutableLiveData<ResponseModel> responseModelMutableLiveData;

    public MainActivityViewModel() {
        informationAPIService = new InformationAPIService();
    }

    public LiveData<ResponseModel> getInformation() {
        if (responseModelMutableLiveData == null) {
            responseModelMutableLiveData = informationAPIService.getInformation();
        }
        return responseModelMutableLiveData;
    }
}
