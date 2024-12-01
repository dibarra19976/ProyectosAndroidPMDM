package com.daniibarra.pantallatactil;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        TextView entrada = (TextView)findViewById(R.id.TextViewEntrada);
        entrada.setOnTouchListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView sortida = (TextView) findViewById(R.id.TextViewSortida);
        String accions[] = { "ACTION_DOWN", "ACTION_UP", "ACTION_MOVE",
                "ACTION_CANCEL","ACTION_OUTSIDE", "ACTION_POINTER_DOWN",
                "ACTION_POINTER_UP" };
        int accio = motionEvent.getAction();
        int codiAccio = accio & MotionEvent.ACTION_MASK;
        int iPunter = (accio & MotionEvent.ACTION_POINTER_INDEX_MASK)
                >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        sortida.append(accions[codiAccio] +"\n");
        for (int i = 0; i < motionEvent.getPointerCount(); i++) {
            sortida.append(
                    " puntero:" + motionEvent.getPointerId(i) + " index: " + i +
                    " x:" + motionEvent.getX(i) + " y:" + motionEvent.getY(i));
        }
        sortida.append("\n");
        return true;
    }
}