package com.daniibarra.repasoexamenud4_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button buttonComunicacion;

    Button buttonIntenciones;
    Button buttonLista;

    public static class scoreStorage{
        static ArrayList<String> strings = new ArrayList<String>();


        public static ArrayList<String> getListaStrings(){
            strings.add("string");
            strings.add("string");
            strings.add("string");
            strings.add("string");
            strings.add("string");
            strings.add("string");

            return strings;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //FIND VIEW
        buttonComunicacion = findViewById(R.id.buttonComunicacion);
        buttonIntenciones = findViewById(R.id.buttonIntenciones);
        buttonLista = findViewById(R.id.buttonLista);

        //LISTENERS
        buttonComunicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openComuncacion(view);
            }
        });

        buttonIntenciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIntenciones(view);
            }
        });

        buttonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLista(view);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // METODOS

    public void openComuncacion(View view){
        Intent i = new Intent(this, Comunicacion_Primera_View.class);
        startActivity(i);
    }

    public void openIntenciones(View view){
        Intent i = new Intent(this, Intenciones_Implicitas.class);
        startActivity(i);
    }


    public void openLista(View view){
        Intent i = new Intent(this, Lista.class);
        startActivity(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.preferences){
            launchPreferences(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void launchPreferences(View view){
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);

    }
}