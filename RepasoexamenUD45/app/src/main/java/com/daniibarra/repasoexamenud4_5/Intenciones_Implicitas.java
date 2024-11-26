package com.daniibarra.repasoexamenud4_5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daniibarra.repasoexamenud4_5.R;

public class Intenciones_Implicitas extends AppCompatActivity {
    Button webBtn;
    Button tlfBtn;
    Button emailBtn;
    Button mapsBtn;
    Button camaraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intenciones_implicitas);
        webBtn = findViewById(R.id.webBtn);

        webBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                webIntent((view));
            }
        });

        tlfBtn = findViewById(R.id.tlfBtn);

        tlfBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                tlfIntent((view));
            }
        });

        emailBtn = findViewById(R.id.emailBtn);

        emailBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                emailIntent((view));
            }
        });
        mapsBtn = findViewById(R.id.mapsBtn);

        mapsBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mapsIntent((view));
            }
        });

        camaraBtn = findViewById(R.id.camaraBtn);

        camaraBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                camaraIntent((view));
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void webIntent(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iesjoanramis.org/"));
        startActivity(i);
    }
    public void tlfIntent(View view){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:971000000"));
        startActivity(i);
    }
    public void mapsIntent(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.887642,4.254319"));
        startActivity(i);
    }
    public void emailIntent(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "assumpte");
        i.putExtra(Intent.EXTRA_TEXT, "text del correu");
        i.putExtra(Intent.EXTRA_EMAIL, new String[] {"info@iesjoanramis.org"});
        startActivity(i);
    }

    public void camaraIntent(View view){
        Intent i = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
        startActivity(i);
    }
}