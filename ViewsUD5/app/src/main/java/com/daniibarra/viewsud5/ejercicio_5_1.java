package com.daniibarra.viewsud5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ejercicio_5_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ExempleView(this));
    }
    public class ExempleView extends View {
        public ExempleView (Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            Paint pinzell = new Paint();
            pinzell.setTextAlign(Paint.Align.CENTER);
            int xPosition = (canvas.getWidth() / 2);
            int yPosition = (int) ((canvas.getHeight() / 2) - ((pinzell.descent() + pinzell.ascent()) / 2)) ;
            pinzell.setColor(Color.BLUE);
            pinzell.setStrokeWidth(20);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(350, 450, 100, pinzell);
            pinzell.setColor(Color.BLACK);
            canvas.drawCircle(575, 450, 100, pinzell);
            pinzell.setColor(Color.RED);
            canvas.drawCircle(800, 450, 100, pinzell);
            pinzell.setColor(Color.YELLOW);
            canvas.drawCircle(470,550,100,pinzell);
            pinzell.setColor(Color.GREEN);
            canvas.drawCircle(695,550,100,pinzell);

        }
    }
}