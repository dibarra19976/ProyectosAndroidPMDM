package com.daniibarra.ast3roides;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class GameActivity extends AppCompatActivity {
    private AsteroidsView gameView;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = (AsteroidsView) findViewById(R.id.AsteroidsView);
        createNotificationChannel();
        sendNotification();
    }

    @Override protected void onPause() {
        super.onPause();
        gameView.deactivateSensors();
        gameView.getThread().pause();
    }
    @Override protected void onResume() {
        super.onResume();
        gameView.getThread().unpause();
        gameView.activateSensors();
    }
    @Override protected void onDestroy() {
        gameView.getThread().halt();
        gameView.deactivateSensors();
        super.onDestroy();
    }


    public void createNotificationChannel(){
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
// Create a NotificationChannel
            NotificationChannel notificationChannel = new
                    NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Music Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Creant el servei de música");
            mNotifyManager.createNotificationChannel(notificationChannel);}
    }
    private NotificationCompat.Builder getNotificationBuilder(){

        NotificationCompat.Builder notifyBuilder = new
                NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("ASTEROIDES")
                .setContentText("Ejecutando juego.")
                .setSmallIcon(R.mipmap.ic_game);
        return notifyBuilder;
    }
    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder =
                getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }
}