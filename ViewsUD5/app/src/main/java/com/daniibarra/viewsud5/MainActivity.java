package com.daniibarra.viewsud5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button ej5_1;
    Button ej5_2;
    Button ej5_3;
    Button ej5_5;
    Button ej5_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ej5_1 = findViewById(R.id.ej5_1);

        ej5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEj5_1((view));
            }
        });

        ej5_2 = findViewById(R.id.ej5_2);

        ej5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEj5_2((view));
            }
        });

        ej5_3 = findViewById(R.id.ej5_3);

        ej5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEj5_3((view));
            }
        });

        ej5_5 = findViewById(R.id.ej5_5);

        ej5_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEj5_5((view));
            }
        });

        ej5_6 = findViewById(R.id.ej5_6);

        ej5_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEj5_6((view));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void showEj5_1(View view) {
        Intent i = new Intent(this, ejercicio_5_1.class);
        startActivity(i);
    }
    public void showEj5_2(View view) {
        Intent i = new Intent(this, ejercicio_5_2.class);
        startActivity(i);
    }
    public void showEj5_3(View view) {
        Intent i = new Intent(this, ejercicio_5_3.class);
        startActivity(i);
    }
    public void showEj5_5(View view) {
        Intent i = new Intent(this, ejercicio_5_5.class);
        startActivity(i);
    }
    public void showEj5_6(View view) {
        Intent i = new Intent(this, ejercicio_5_6.class);
        startActivity(i);
    }
}