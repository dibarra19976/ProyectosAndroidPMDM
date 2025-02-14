package com.daniibarra.ast3roides;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceptorBateria extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, About.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}