package com.daniibarra.ast3roides;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AsteroidsView extends View {
    private AsteroidsGraphic ship; // Gràfic de la nau
    private int angleShip; // Angle de gir de la nau
    private float accelShip; // Augment de velocitat
    private static final double SHIP_MAX_SPEED = 50;
    // Increment estàndar de gir i acceleració
    private static final int STEPSIZE_ROT_SHIP = 5;
    private static final float STEPSIZE_ACCEL_SHIP = 0.5f;

    // //// ASTEROIDS //////
    private List<AsteroidsGraphic> asteroids; // Vector amb el Asteroides
    private int numAsteroids = 5; // Número inicial d'asteroides
    private int numFragments = 3; // Fragments en que es divideix
    public AsteroidsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableShip, drawableAsteroid, drawableMissile;

        SharedPreferences pref =PreferenceManager.getDefaultSharedPreferences(getContext());
        if (pref.getString("grafics", "1").equals("0")) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            Path pathAsteroid = new Path();
            pathAsteroid.moveTo((float) 0.3, (float) 0.0);
            pathAsteroid.lineTo((float) 0.6, (float) 0.0);
            pathAsteroid.lineTo((float) 0.6, (float) 0.3);
            pathAsteroid.lineTo((float) 0.8, (float) 0.2);
            pathAsteroid.lineTo((float) 1.0, (float) 0.4);
            pathAsteroid.lineTo((float) 0.8, (float) 0.6);
            pathAsteroid.lineTo((float) 0.9, (float) 0.9);
            pathAsteroid.lineTo((float) 0.8, (float) 1.0);
            pathAsteroid.lineTo((float) 0.4, (float) 1.0);
            pathAsteroid.lineTo((float) 0.0, (float) 0.6);
            pathAsteroid.lineTo((float) 0.0, (float) 0.2);
            pathAsteroid.lineTo((float) 0.3, (float) 0.0);


            ShapeDrawable dAsteroid = new ShapeDrawable(new PathShape(pathAsteroid, 1, 1));
            dAsteroid.getPaint().setColor(Color.WHITE);
            dAsteroid.getPaint().setStyle(Paint.Style.STROKE);
            dAsteroid.setIntrinsicWidth(50);
            dAsteroid.setIntrinsicHeight(50);
            drawableAsteroid = dAsteroid;

            Path pathShip = new Path();
            pathShip.lineTo((float) 0, (float) 0);
            pathShip.lineTo((float) 0, (float) 1);
            pathShip.lineTo((float) 1, (float) 0.5);

            ShapeDrawable dShip = new ShapeDrawable(new PathShape(pathShip, 1, 1));
            dShip.getPaint().setColor(Color.WHITE);
            dShip.getPaint().setStyle(Paint.Style.STROKE);
            dShip.setIntrinsicHeight(50);
            dShip.setIntrinsicWidth(50);

            drawableShip = dShip;
            setBackgroundColor(Color.BLACK);
        } else {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
            drawableAsteroid = context.getResources().getDrawable(R.drawable.asteroide1);
            drawableShip = context.getResources().getDrawable(R.drawable.nave);

        }

        asteroids = new ArrayList<>();
        for (int i = 0; i < numAsteroids; i++) {
            AsteroidsGraphic asteroid = new AsteroidsGraphic(this,drawableAsteroid);
            asteroid.setIncY(Math.random() * 4 - 2);

            asteroid.setIncX(Math.random() * 4 - 2);
            asteroid.setRotAngle((int) (Math.random() * 360));
            asteroid.setRotSpeed((int) (Math.random() * 8 - 4));
            asteroids.add(asteroid);


        }
        ship = new AsteroidsGraphic(this,drawableShip);

    }
    @Override protected void onSizeChanged(int width, int height,int prevWidth, int prevHeight) {
        super.onSizeChanged(width, height, prevWidth, prevHeight);
// Un cop coneixem el nostre ample i alt.
        ship.setCenX((int)width/2);
        ship.setCenY((int)height/2);
        for (AsteroidsGraphic asteroid: asteroids) {
            do {
                asteroid.setCenX((int)(Math.random() * width));
                asteroid.setCenY((int)(Math.random() * height));
            }while (asteroid.distance(ship) < (width+height)/5)

            ;
        }

    }
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (AsteroidsGraphic asteroid: asteroids) {
            asteroid.drawGraphic(canvas);
        }
        ship.drawGraphic(canvas);
    }
}