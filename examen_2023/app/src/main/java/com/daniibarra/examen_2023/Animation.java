package com.daniibarra.examen_2023;

import android.content.Intent;
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
    Button buttonAnimacionSalir;
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
        buttonAnimacionSalir = findViewById(R.id.buttonAnimacionSalir);
        imageView = findViewById(R.id.imageViewAnimacion);
        textViewAnimacionTiempo = findViewById(R.id.textViewAnimacionTiempo);

        animacio = (AnimationDrawable) ContextCompat.getDrawable(this,R.drawable.animacion);
        imageView.setImageDrawable(animacio);


        buttonAnimacionStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!animacio.isRunning()){
                    animacio.start();
                    startTime = System.currentTimeMillis();
                }

            }
        });
        buttonAnimacionSalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                 onSortir();;
            }
        });


        buttonAnimacionParar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(animacio.isRunning()){
                    animacio.stop();
                    finishTime = System.currentTimeMillis();
                    totalTime = totalTime + (finishTime-startTime);
                    Double time = (double) totalTime/1000;
                    textViewAnimacionTiempo.setText(""+time);
                }

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

    public void onSortir(){
        Intent intent = new Intent();
        animacio.stop();
        finishTime = System.currentTimeMillis();
        totalTime = totalTime + (finishTime-startTime);
        Double time = (double) totalTime/1000;
        intent.putExtra("resultat",""+time);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        onSortir();
        super.onBackPressed();

    }
}