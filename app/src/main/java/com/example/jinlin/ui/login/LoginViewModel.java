package com.example.jinlin.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jinlin.getandpost.Message;
import com.google.gson.Gson;

public class LoginViewModel extends ViewModel implements Message {

    private MutableLiveData<String>[] allMerchant;
    private Gson gson;
    public LoginViewModel(){
        allMerchant = new MutableLiveData[2];
        gson = new Gson();
    }

    @Override
    public void tranStr(String str) {
        
    }
}
