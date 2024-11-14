package com.daniibarra.examen_2023;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Animation extends AppCompatActivity {

    AnimationDrawable animacio;
    Button buttonAnimacionStart;
    Button buttonAnimacionParar;
    ImageView imageView;
    Long totalTime = (long) 0;
    Long startTime;
    Long finishTime;
    TextView textViewAnimacionTiempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animation);


        buttonAnimacionStart = findViewById(R.id.buttonAnimacionStart);
        buttonAnimacionParar = findViewById(R.id.buttonAnimacionParar);
        imageView = findViewById(R.id.imageViewAnimacion);
        textViewAnimacionTiempo = findViewById(R.id.textViewAnimacionTiempo);

        animacio = (AnimationDrawable) ContextCompat.getDrawable(this,R.drawable.animacion);
        imageView.setImageDrawable(animacio);


        buttonAnimacionStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                animacio.start();
                startTime = System.currentTimeMillis();
            }
        });


        buttonAnimacionParar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                animacio.stop();
                finishTime = System.currentTimeMillis();
                totalTime = totalTime + (finishTime-startTime);
                textViewAnimacionTiempo.setText(totalTime.toString());
            }
        });

        //AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
      //  animationDrawable.start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}