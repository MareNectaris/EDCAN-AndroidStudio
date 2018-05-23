package me.ka_mo.a180523;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if (intent.ACTION_SCREEN_ON.equals(intent.getAction()))
            Toast.makeText(context,"hi", Toast.LENGTH_SHORT).show();
    }
}
