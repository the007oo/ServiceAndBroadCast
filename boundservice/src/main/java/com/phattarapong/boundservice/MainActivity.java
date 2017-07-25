package com.phattarapong.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyService myservice;
    private ArrayList<String> values;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doBindService();

        values = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        Button btnReload = (Button) findViewById(R.id.reload_data);
        btnReload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reload_data:
                if(myservice != null){
                    ArrayList<String> wordlist = myservice.getWordList();
                    values.clear();
                    values.addAll(wordlist);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
    private void doBindService(){
        ServiceConnection cn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myservice = ((MyService.MyBinder)service).getService();
                Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            myservice=null;
            }
        };
        Intent i = new Intent(this,MyService.class);
        bindService(i,cn, Context.BIND_AUTO_CREATE);
    }
}
