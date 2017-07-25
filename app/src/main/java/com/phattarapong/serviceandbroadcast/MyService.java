package com.phattarapong.serviceandbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Phattarapong on 02-Jul-17.
 */

public class MyService extends Service {

    private MyThread thread;
    private static final String TAG = "ServiceDemo";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
      thread = new MyThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service Started..",Toast.LENGTH_LONG).show();
        Log.d(TAG,"Service Started..");
        if(!thread.isAlive()){
            thread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service Stoped..",Toast.LENGTH_LONG).show();
        Log.d(TAG,"Service Stoped..");
        thread.finish = true;
    }
    private class MyThread extends Thread{

        private static final int DELAY =1000;
        private int i = 0;
        private boolean finish = false;

        public void run(){
            while (true){
                Log.d(TAG, String.valueOf(i++));
                try {
                    sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(finish){
                    return;
                }
            }
        }
    }
}
