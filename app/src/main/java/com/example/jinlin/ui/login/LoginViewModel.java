package com.example.jinlin.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jinlin.User;
import com.example.jinlin.getandpost.Message;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends ViewModel implements Message{

    private MutableLiveData<List<User>> user_info;
    private Gson gson;
    public LoginViewModel(){
        user_info = new MutableLiveData<>();
        gson = new Gson();
    }

    public MutableLiveData<List<User>> getUser_info() {
        return user_info;
    }

    @Override
    public void tranStr(String str) {
        User [] user = gson.fromJson(str, User[].class);
        List <User> temp = new ArrayList<>();
        for(int i = 0; i < user.length; i ++){
            temp.add(user[i]);
        }
        user_info.postValue(temp);
    }

    private String getUserInfo(){
        return "";
    }
}
