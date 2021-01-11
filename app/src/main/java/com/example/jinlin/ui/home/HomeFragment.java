package com.example.jinlin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.lifecycle.Observer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jinlin.DetailActivity;
import com.example.jinlin.Merchant;
import com.example.jinlin.R;
import com.example.jinlin.getandpost.MyGetJob;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    Button toLoginInterface;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<Merchant> allMerchant = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        myAdapter = new MyAdapter();
        myAdapter.setAllMerchant(allMerchant);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //发送Post请求拉取商品信息
        String url = "http://192.168.1.107:1337/merchant/getMerchant";
        MyGetJob get = new MyGetJob();
        get.setMsg(homeViewModel);
        get.execute(url);
        //观察者刷新UI
        homeViewModel.getAllMerchant().observe(getViewLifecycleOwner(), new Observer<List<Merchant>>(){
            @Override
            public void onChanged(List<Merchant> s) {
                allMerchant = s;
                myAdapter.setAllMerchant(allMerchant);
                myAdapter.notifyDataSetChanged();

            }
        });







//        toLoginInterface = root.findViewById(R.id.btn_to_login_interface);
//        toLoginInterface.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("NEW: ", "" + onCreatePop ++);
//
//                Intent intent = new Intent(this.getContext(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
        return root;
    }



}
