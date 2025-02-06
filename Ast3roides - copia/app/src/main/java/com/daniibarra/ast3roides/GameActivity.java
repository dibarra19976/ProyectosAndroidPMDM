package com.daniibarra.ast3roides;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {
    private AsteroidsView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = (AsteroidsView) findViewById(R.id.AsteroidsView);
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
}