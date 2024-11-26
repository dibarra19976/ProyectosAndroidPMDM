package com.daniibarra.repasoexamenud4_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Comunicacion_Primera_View extends AppCompatActivity {

    Button buttonComunicacionSegundo;
    EditText editText;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comunicacion_primera_view);

        buttonComunicacionSegundo = findViewById(R.id.buttonComunicacionSegundo);
        editText = findViewById(R.id.editTextComunicacion);
        text = findViewById(R.id.textViewResultadoComunicacion);
        buttonComunicacionSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSegundaView(view);

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openSegundaView(View view){
        Intent i = new Intent(this, Comunicacion_Segunda_View.class);
        i.putExtra("name", editText.getText().toString());
        startActivityForResult(i, 1234);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234 && resultCode==RESULT_OK) {
            String res = data.getStringExtra("resultat");
            text.setText(res);
        }
    }


}