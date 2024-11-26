package com.daniibarra.examen_2023;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button buttonAnimacion;
    Button buttonVector;
    Button buttonMapa;

    int vector = 0;
    int mapa = 0;

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
                vector++;
                TextView text = findViewById(R.id.textViewVectorEjecuciones);
                text.setText(""+ vector);
            }
        });

        buttonMapa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startMapa(view);
                mapa++;
                TextView text = findViewById(R.id.textViewMapaEjecuciones);
                text.setText(""+ vector);
            }
        });


        loadPreferences();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void startAnimacion(View view){
        Intent intent = new Intent(this, Animation.class);
        startActivityForResult(intent, 1234);
    }
    public void startVector(View view){
        Intent intent = new Intent(this, Vector.class);
        startActivityForResult(intent, 12345);

    }
    public void startMapa(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:40.05722693707509, 4.13093944467142"));
        startActivity(intent);
    }
    public void launchPreferences(View view){
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
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

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234 && resultCode== Activity.RESULT_OK) {
            String res = data.getStringExtra("resultat");
            TextView text = findViewById(R.id.textViewAnimacionTiempo);
            text.setText(res);
        }
    }

    public void loadPreferences(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean theme = pref.getBoolean("nightMode",true);
        if(theme){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
    }
}