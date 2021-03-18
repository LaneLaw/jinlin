package com.example.jinlin.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jinlin.Merchant;
import com.example.jinlin.getandpost.Message;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements Message {
    private MutableLiveData<List<Merchant>> allMerchant;
    Gson gson;

    public HomeViewModel(){
        allMerchant = new MutableLiveData<>();
        gson = new Gson();
    }

    public MutableLiveData<List<Merchant>> getAllMerchant() {
        return allMerchant;
    }



    public void tranStr(String s){
        Merchant [] mer = gson.fromJson(s, Merchant[].class);
        List<Merchant> temp = new ArrayList<>();
        for(int i = 0; i < mer.length; i ++){
            temp.add(mer[i]);
        }
        allMerchant.postValue(temp);
    }
}


