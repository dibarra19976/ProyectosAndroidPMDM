package com.daniibarra.practicaexamen_6_7;

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

    Button soundpool, sensores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        soundpool = findViewById(R.id.button_soundpool);

        soundpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo_soundpool();
            }
        });

        sensores = findViewById(R.id.button_sensores);

        sensores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTo_sensores();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void goTo_soundpool(){
        Intent i = new Intent(this, SoundPoolActivity.class);
        startActivity(i);
    }
    private void goTo_sensores(){
        Intent i = new Intent(this, SensoresActivity.class);
        startActivity(i);
    }
}