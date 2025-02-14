package org.example.serveimusica;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ServeiMusica extends Service {

    MediaPlayer reproductor;
    @Override
    public void onCreate() {
        Toast.makeText(this,"Servei creat",
                Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio2);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int
            idArranque) {
        Toast.makeText(this,"Servei arrencat "+ idArranque,
                Toast.LENGTH_SHORT).show();
        reproductor.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servei detingut",
                Toast.LENGTH_SHORT).show();
        reproductor.stop();
    }
    @Override
    public IBinder onBind(Intent intencio) {
        return null;
    }
}