package org.example.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class IntentServiceOperacio extends IntentService {
    public IntentServiceOperacio() {
        super("IntentServiceOperacio");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        double n = intent.getExtras().getDouble("numero");
        SystemClock.sleep(25000);
        Intent resultatIntent = new
        Intent(MainActivity.receptorOperacio.ACTION_RESP);
        resultatIntent.putExtra("resultado", n * n);
        LocalBroadcastManager.getInstance(this).sendBroadcast(resultatIntent);
    }
}