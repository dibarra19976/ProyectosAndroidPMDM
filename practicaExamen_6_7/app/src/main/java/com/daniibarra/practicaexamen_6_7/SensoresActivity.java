package com.daniibarra.practicaexamen_6_7;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class SensoresActivity extends AppCompatActivity implements SensorEventListener {


    List<Sensor> llistaSensors;
    SensorManager sm;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sensores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         sm = (SensorManager)
        getSystemService(SENSOR_SERVICE);
        //llistaSensors = sm.getSensorList(Sensor.TYPE_ALL);
        //para especificos
        text = findViewById(R.id.textView);

        llistaSensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        activateSensors();
    }

    @Override public void onAccuracyChanged(Sensor sensor, int
            accuracy) {}

    @Override public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
           /* int n = 0;
            for (Sensor sensor: llistaSensors) {
                if (event.sensor == sensor) {
                    for (int i=0; i<event.values.length; i++) {
                    }
                }
                n++;
            }*/
            float value = event.values[1];
            text.setText(""+event.values[1] + " " + event.values[0]);

        }


    }

    public void activateSensors(){
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (!sensorList.isEmpty()) {
            Sensor accelerometerSensor = sensorList.get(0);
            sm.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }
    public void deactivateSensors(){
        sm.unregisterListener(this);
    }
}