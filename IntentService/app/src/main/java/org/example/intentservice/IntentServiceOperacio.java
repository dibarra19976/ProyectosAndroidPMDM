package org.example.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

public class IntentServiceOperacio extends IntentService {
    public IntentServiceOperacio() {
        super("IntentServiceOperacio");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        double n = intent.getExtras().getDouble("numero");
        SystemClock.sleep(25000);
        MainActivity.sortida.append(n*n + "\n");
    }
}