package com.daniibarra.ejercicio66;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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

    Button btn;
    EditText text;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.buttonCopy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = text.getText().toString();
                MyTask task = new MyTask();
                task.execute(fileName);
            }
        });

        text = findViewById(R.id.editTextName);
        result = findViewById(R.id.textViewResult);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    class MyTask extends AsyncTask<String, Integer, Integer> {
        private ProgressDialog progres;
        private String fileName;

        @Override protected void onPreExecute() {
            progres = new ProgressDialog(MainActivity.this);
            progres.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progres.setMessage("REALIZANT COPIA DE SEGURETAT");
            progres.setCancelable(true);
            progres.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                @Override public void onCancel(DialogInterface dialog) {
                    MyTask.this.cancel(true);
                }
            });
            progres.setMax(100);
            progres.setProgress(0);
            progres.show();
        }
        @Override protected Integer doInBackground(String... params) {
            fileName = params[0];
            int fileSize = (int) (Math.random() * (9000 - 400)) + 400;
            for (int i = 1; i <= 10 && !isCancelled(); i++) {
                SystemClock.sleep(1000);
                publishProgress(i*10);
            }
            return fileSize;
        }
        @Override protected void onProgressUpdate(Integer... perc) {
            progres.setProgress(perc[0]);
        }
        @Override protected void onPostExecute(Integer fileSize) {
            progres.dismiss();
            result.setText("Copia de seguretat finalitzada\nS'ha copiat el fitxer " + fileName +" amb un total de " + fileSize + " bytes");

        }
        @Override protected void onCancelled() {
            result.setText("Copia de seguretat de "+ fileName+ " cancelada");
        }

    }
}