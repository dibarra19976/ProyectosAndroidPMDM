package com.daniibarra.comunicacioambretorn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView textViewSecond;
    Button acceptarBtn;
    Button rebutjarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        textViewSecond = findViewById(R.id.textViewSecond);
        String nom;
        Intent i = getIntent();
        nom = i.getStringExtra("name");

        textViewSecond.append("Benvingut " + nom + ", acceptes les condicions?");


        acceptarBtn = findViewById(R.id.acceptarBtn);
        acceptarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptat();
            }
        });


        rebutjarBtn = findViewById(R.id.rebutjarBtn);
        rebutjarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebutjat();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void acceptat(){
        Intent intent = new Intent();
        intent.putExtra("resultat", "Acceptat"); // Reemplaza "valor" con el dato que quieras enviar
        setResult(RESULT_OK, intent);
        finish();
    }

    public void rebutjat(){
        Intent intent = new Intent();
        intent.putExtra("resultat", "Rebutjat"); // Reemplaza "valor" con el dato que quieras enviar
        setResult(RESULT_OK, intent);
        finish();
    }

}