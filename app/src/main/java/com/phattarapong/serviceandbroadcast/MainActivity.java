package com.phattarapong.serviceandbroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        Button btnStart,btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent (this,MyService.class);
        stopService(i);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btn_start:
                i = new Intent(this,MyService.class);
                startService(i);
                break;
            case R.id.btn_stop:
                i = new Intent(this,MyService.class);
                stopService(i);
                        break;
        }
    }
}
