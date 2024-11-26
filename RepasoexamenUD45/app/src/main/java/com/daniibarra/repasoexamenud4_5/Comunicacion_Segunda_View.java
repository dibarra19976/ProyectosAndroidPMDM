package com.daniibarra.repasoexamenud4_5;

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

public class Comunicacion_Segunda_View extends AppCompatActivity {


    Button btn1;
    Button btn2;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comunicacion_segunda_view);



        btn1 = findViewById(R.id.buttonResultComunicacion1);
        btn2 = findViewById(R.id.buttonResultComunicacion2);
        text = findViewById(R.id.textViewResultadoSegundo);

        Bundle extras = getIntent().getExtras();
        String s = extras.getString("name");
        text.setText(s);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnPrimeraView(view, "infinite bacon");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnPrimeraView(view, "infinite games");

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void returnPrimeraView(View view, String texto){
        Intent intent = new Intent();
        intent.putExtra("resultat",texto);
        setResult(RESULT_OK, intent);
        finish();
    }
}