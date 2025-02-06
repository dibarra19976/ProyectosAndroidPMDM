package com.daniibarra.practicaexamen_6_7;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SoundPoolActivity extends AppCompatActivity {

    SoundPool soundPool;
    int sound1, sound2;
    Button button_sound1, button_sound2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sound_pool);

        soundPool = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        sound1 = soundPool.load(this, R.raw.sound1, 0);
        sound2 = soundPool.load(this, R.raw.sound2, 0);


        button_sound1 = findViewById(R.id.button_sound1);
        button_sound2 = findViewById(R.id.button_sound2);
        button_sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound1, 1, 1, 1, 0, 1);
            }
        });
        button_sound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound2, 1, 1, 1, 0, 1);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}