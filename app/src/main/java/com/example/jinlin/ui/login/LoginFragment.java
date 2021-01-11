package com.example.jinlin.ui.login;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.jinlin.LoginActivity;
import com.example.jinlin.MainActivity;
import com.example.jinlin.R;
import com.example.jinlin.RegisterActivity;
import com.example.jinlin.TestActivity;
import com.example.jinlin.TestService;
import com.example.jinlin.getandpost.MyPostJob;
import com.example.jinlin.ui.home.HomeViewModel;

public class LoginFragment extends Fragment {
    LoginViewModel loginViewModel;
    Button login, register;
    EditText account, password;
    SeekBar musicSeek;
    TestService Tservice;
    TestService.MyBinder binder;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    updateProgress();
                    break;
            }
        }
    };
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (TestService.MyBinder) service;
            Tservice = binder.getService();
            musicSeek.setMax(binder.getDuration());
            musicSeek.setProgress(binder.getCurrentPostion());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LoginFragment tt = this;
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        account = root.findViewById(R.id.edit_account);
        password = root.findViewById(R.id.edit_password);
        login = root.findViewById(R.id.btn_login);
        register = root.findViewById(R.id.btn_register);


        Intent intent = new Intent(getContext(), TestService.class);
        getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        musicSeek = root.findViewById(R.id.seekBar_music);
        musicSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    binder.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_str = "http://192.168.42.188:1337/user/login";
                new MyPostJob(tt.getContext()).execute(url_str, account.getText().toString(), password.getText().toString());

                play();
                handler.sendEmptyMessage(1);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(binder != null){
            handler.sendEmptyMessage(1);
        }

    }

    private void updateProgress(){
        int current = binder.getCurrentPostion();
        musicSeek.setProgress(current);
        handler.sendEmptyMessageDelayed(1, 500);
    }
    public void play(){
        binder.play();
    }
}
