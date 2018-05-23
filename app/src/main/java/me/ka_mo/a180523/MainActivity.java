package me.ka_mo.a180523;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,Service.class);
        startService(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public void on(View v){
        startService(intent);
        Toast.makeText(getApplicationContext(),"켜짐",Toast.LENGTH_SHORT).show();
    }
    public void off(View v){
        stopService(intent);
        Toast.makeText(getApplicationContext(),"꺼짐",Toast.LENGTH_SHORT).show();
    }
}
