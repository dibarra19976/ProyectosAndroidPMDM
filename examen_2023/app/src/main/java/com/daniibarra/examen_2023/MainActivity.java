package com.daniibarra.examen_2023;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button buttonAnimacion;
    Button buttonVector;
    Button buttonMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonAnimacion = (Button) findViewById(R.id.buttonAnimacion);
        buttonVector = (Button) findViewById(R.id.buttonVector);
        buttonMapa = (Button) findViewById(R.id.buttonMapa);


        buttonAnimacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startAnimacion(view);
            }
        });

        buttonVector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startVector(view);
            }
        });

        buttonMapa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startMapa(view);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void startAnimacion(View view){
        Intent intent = new Intent(this, Animation.class);
        startActivity(intent);
    }
    public void startVector(View view){
        Intent intent = new Intent(this, Vector.class);
        startActivity(intent);
    }
    public void startMapa(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:39.887642,4.254319"));
        startActivity(intent);
    }
}