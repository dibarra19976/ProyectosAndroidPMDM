package org.example.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {
    private EditText entrada;
    public static TextView sortida;

    static ReceptorOperacio receptorOperacio = new ReceptorOperacio();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada= (EditText) findViewById(R.id.entrada);
        sortida = (TextView) findViewById(R.id.sortida);
        IntentFilter filtre = new IntentFilter(ReceptorOperacio.ACTION_RESP);
        filtre.addCategory(Intent.CATEGORY_DEFAULT);
        LocalBroadcastManager.getInstance(this).registerReceiver(receptorOperacio,
                filtre);
    }
    public void calcularOperacio(View view) {
        double n = Double.parseDouble(entrada.getText().toString());
        sortida.append(n +"^2 = " );
        //Intent i = new Intent(this, ServeiOperacio.class);
        Intent i = new Intent(this, IntentServiceOperacio.class);
        sortida.append((n*n)+"\n");

        i.putExtra("numero", n);

        startService(i);
    }

    static class ReceptorOperacio extends BroadcastReceiver {
        public static final String ACTION_RESP=
                "com.example.intentservice.intent.action.RESPUESTA_OPERACION";
        @Override
        public void onReceive(Context context, Intent intent) {
            Double res = intent.getDoubleExtra("resultado",
                    0.0);
            MainActivity.sortida.append(" "+ res);
        }
    }
}
