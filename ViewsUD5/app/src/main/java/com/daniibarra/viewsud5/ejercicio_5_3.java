package com.daniibarra.viewsud5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Vector;

public class ejercicio_5_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ejercicio_5_3.ExempleView(this));
    }
    public class ExempleView extends View {
        public ExempleView (Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawColor(Color.BLACK);
            //mostrar los dos vectores

            Drawable imagen1;
            imagen1 = ContextCompat.getDrawable(ejercicio_5_3.this, R.drawable.icon_flat_linux);
            imagen1.setBounds(30,30,400,400);
            imagen1.draw(canvas);


            Drawable imagen2;
            imagen2 = ContextCompat.getDrawable(ejercicio_5_3.this, R.drawable.baseline_arrow_downward_24);
            imagen2.setBounds(300,30,600,400);
            imagen2 .draw(canvas);
        }
    }
}