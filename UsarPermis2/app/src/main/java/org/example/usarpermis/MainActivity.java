package org.example.usarpermis;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verificarPermisos();
    }

    public void veureVideo (View view){
        Intent i = new Intent();
        i.setClassName("com.payperview.serveis",
                "com.payperview.serveis.VeureVideo");
        startActivity(i);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int REQUEST_CODE = 200;
        int PermisosVideo = ContextCompat.checkSelfPermission(this,
                "com.payperview.serveis.VEURE_VIDEOS");
        if (PermisosVideo != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {
                    "com.payperview.serveis.VEURE_VIDEOS"},REQUEST_CODE);
        };
    }
}