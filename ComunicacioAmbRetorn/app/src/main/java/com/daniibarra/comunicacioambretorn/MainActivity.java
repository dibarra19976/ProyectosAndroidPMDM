package com.daniibarra.comunicacioambretorn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button verificarBtn;
    private EditText nomInput;
    private TextView resultadoText;

    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        verificarBtn = findViewById(R.id.verificarBtn);
        verificarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        runSecondActivity((view));
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    public void runSecondActivity(View view){
        nomInput = (EditText) findViewById(R.id.nomInput);
        nom = nomInput.getText().toString();
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("name", nom );
        startActivityForResult(i, 1234);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        resultadoText = (TextView) findViewById(R.id.resultadoText);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1234 && resultCode==RESULT_OK) {
            String res = data.getStringExtra("resultat");
            resultadoText.setText("Resultat: " + res);
        }
    }
}