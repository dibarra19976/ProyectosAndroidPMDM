package com.daniibarra.ast3roides;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button scoreBtn;
    Button aboutBtn;
    Button settingsBtn;
    Button playBtn;
    public static ScoreStorage scoreStorage= new ScoreStorageList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        TextView text = (TextView) findViewById(R.id.textView);
        scoreBtn = findViewById(R.id.scoreBtn);
        aboutBtn = findViewById(R.id.aboutBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        playBtn = findViewById(R.id.playBtn);


        Animation animacio = AnimationUtils.loadAnimation(this,R.anim.gir_amb_zoom);
        text.startAnimation(animacio);

        Animation animacio2 = AnimationUtils.loadAnimation(this,R.anim.apareixer);
        playBtn.startAnimation(animacio2);

        Animation animacio3 = AnimationUtils.loadAnimation(this,R.anim.despl_dreta);
        settingsBtn.startAnimation(animacio3);


        Animation animacio4 = AnimationUtils.loadAnimation(this,R.anim.scale_from_0);
        aboutBtn.startAnimation(animacio4);


        Animation animacio5 = AnimationUtils.loadAnimation(this,R.anim.despli_baix);
        scoreBtn.startAnimation(animacio5);
        Animation animacio6 = AnimationUtils.loadAnimation(this,R.anim.gir_amb_zoom);


        scoreBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showScores((view));
            }
        });
        aboutBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){

                view.startAnimation(animacio6);
                runAboutClass((view));
            }
        });
        playBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                runGame((view));
            }
        });
        settingsBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchPreferences(((view)));

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void runAboutClass(View view){
        Intent i = new Intent(this, About.class);
        startActivity(i);
    }
    public void launchPreferences(View view){
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.preferences){
            launchPreferences(null);
        }
        if (id == R.id.about){
            runAboutClass(null);
        }
        return super.onOptionsItemSelected(item);
    }
    public void showScores(View view) {
        Intent i = new Intent(this, Scores.class);
        startActivity(i);
    }
    public void runGame(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
}