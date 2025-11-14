package com.example.truenorthdemoproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;

public class RestartServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, LocationService.class);
        ContextCompat.startForegroundService(context, serviceIntent);
    }
}
