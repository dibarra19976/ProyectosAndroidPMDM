package com.daniibarra.viewsud5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ejercicio_5_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ejercicio_5_2.ExempleView(this));
    }
    public class ExempleView extends View {
        public ExempleView (Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            Path cami = new Path();


            Paint pinzell = new Paint();

            pinzell.setTextAlign(Paint.Align.CENTER);

            int xPosition = (canvas.getWidth() / 2);
            int yPosition = (int) ((canvas.getHeight() / 2) - ((pinzell.descent() + pinzell.ascent()) / 2)) ;

            cami.addCircle(xPosition, yPosition, 150, Path.Direction.CW);
            canvas.drawColor(Color.WHITE);

            pinzell.setColor(Color.BLUE);
            pinzell.setStrokeWidth(8);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawPath(cami, pinzell);
            pinzell.setStrokeWidth(1);
            pinzell.setStyle(Paint.Style.FILL);
            pinzell.setTextSize(30);
            pinzell.setTypeface(Typeface.SANS_SERIF);
            canvas.drawTextOnPath("Desenvolupament d'aplicacions per a mòbils amb Android", cami, 10, -20, pinzell);

        }
    }
}