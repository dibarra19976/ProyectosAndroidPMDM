package com.daniibarra.gravadora;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Gravadora";
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private static String fitxer = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gravar(View view) {
//Crea un nou objecte MediaRecorder
        mediaRecorder = new MediaRecorder();
//Estableix el fitxer declarat a l’inici com a fitxer de sortida
        mediaRecorder.setOutputFile(fitxer);
//Estableix el micròfon com a font d'entrada d'àudio
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
//Estableix el format de sortida com a THREE_GPP
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//Estableix el codificador com a AMR_NB
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//Prepara i inicia la gravació
       try {
           mediaRecorder.prepare();
           mediaRecorder.start();
       }catch (IOException e){
           e.printStackTrace();
       }
    }
    public void aturarGravacio(View view) {
//Atura la gravació i allibera el recurs
        if(mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }

    }
    public void reproduir(View view) {
//Crea un nou objecte MediaPlayer
//Estableix la font com el fitxer que acabes de gravar
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fitxer);
            mediaPlayer.prepare();
            mediaPlayer.start();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
