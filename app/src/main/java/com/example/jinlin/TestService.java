package com.example.jinlin;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class TestService extends Service {
    private MediaPlayer player;
    private String path = "hw_product/media/Pre-loaded/Music/Last_Stop.mp3";

    public class MyBinder extends Binder{
        public TestService getService(){
            return TestService.this;
        }

        public boolean isPlaying(){
            return player.isPlaying();
        }

        public void play(){
            if(player.isPlaying()){
                player.pause();
            } else{
                player.start();
            }
        }

        public int getDuration(){
            return player.getDuration();
        }

        public int getCurrentPostion(){
            return player.getCurrentPosition();
        }
        public void seekTo(int mesc){
            player.seekTo(mesc);
        }
    }
    private MyBinder myBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        try{
            player.setDataSource(path);
            player.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
