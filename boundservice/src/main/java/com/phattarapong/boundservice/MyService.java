package com.phattarapong.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Phattarapong on 02-Jul-17.
 */

public class MyService extends Service {

    private static final long UPDATE_INTERVAL = 5000;
    private  final IBinder mBinder = new MyBinder();
    private Timer timer = new Timer();
    private ArrayList<String> list = new ArrayList<String>();
    private String[] fixedList = {"Java","Php","C++","C#","Visual Basic","Python","Ruby"};
    private int i = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        pollForUpdates();
    }
    private void pollForUpdates(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                list.add(fixedList[i++]);
                if(i>=fixedList.length){
                    i = 0;
                }
                if(list.size() > 5){
                    list.remove(0);
                }
            }
        },0,UPDATE_INTERVAL);
    }

    @Override
    public void onDestroy() {
        if(timer !=null){
            timer.cancel();
        }
    }

    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
    public ArrayList<String> getWordList(){
        return list;
    }
}
