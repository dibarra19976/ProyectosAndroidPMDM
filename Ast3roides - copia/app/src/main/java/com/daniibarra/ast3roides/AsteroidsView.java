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
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AsteroidsView extends View implements SensorEventListener {
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

    // //// THREAD I TEMPS //////
// Thread encarregat de processar el joc
    private GameThread thread = new GameThread();
    // Cada quan volem processar canvis (ms)
    private static int ANIM_INTERVAL = 50;
    // Quan es va realitzar el darrer procés
    private long prevUpdate = 0;

    private float mX = 0, mY = 0;
    private boolean fire = false;


    ///SENSORS///
    private float initValue;
    private boolean initValueValid = false;

    // //// MISIL //////
    private AsteroidsGraphic missile;
    private static int MISSILE_SPEED = 12;
    private boolean missileActive = false;
    private int missileLifetime;
    private List<AsteroidsGraphic> missiles = new ArrayList<>();
    private List<Double> missileLifetimes = new ArrayList<>();
    Drawable drawableMissile;
    
    /// Controls ///
    String controls ;
    SensorManager mSensorManager;

    /// SOns ///
    SoundPool soundPool;
    int idFire, idExplosion;

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(controls.equals("1")){
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    fire = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    float dx = Math.abs(x - mX);
                    float dy = Math.abs(y - mY);
                    if (dy < 6 && dx > 6) {
                        ship.setRotSpeed(Math.round((x - mX) / 2));
                        fire = false;
                    } else if (dx < 6 && dy > 6) {
                        accelShip = Math.round((mY - y) / 25);
                        fire = false;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    ship.setRotSpeed(0);
                    accelShip = 0;
                    if (fire) {
                        fireMissile();
                    }
                    break;
            }
            mX = x;
            mY = y;
        }
        return true;
    }


    class GameThread extends Thread {
        private boolean paused, running;
        public synchronized void pause() {
            paused = true;
        }
        public synchronized void unpause() {
            paused = false;
            notify();
        }
        public void halt() {
            running = false;
            if (paused) unpause();
        }
        @Override public void run() {
            running = true;
            while (running) {
                updateView();
                synchronized (this) {
                    while (paused)
                        try {
                            wait();
                        } catch (Exception e) {
                        }
                }
            }
        }
    }

    public GameThread getThread() {
        return thread;
    }

    public void setThread(GameThread thread) {
        this.thread = thread;
    }

    public AsteroidsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableShip, drawableAsteroid;

        SharedPreferences pref =PreferenceManager.getDefaultSharedPreferences(getContext());

        controls = pref.getString("controls","2");

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

            ShapeDrawable dMissile = new ShapeDrawable(new RectShape());
            dMissile.getPaint().setColor(Color.WHITE);
            dMissile.getPaint().setStyle(Paint.Style.STROKE);
            dMissile.setIntrinsicWidth(15);
            dMissile.setIntrinsicHeight(3);
            drawableMissile = dMissile;
        } else {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
            drawableAsteroid = context.getResources().getDrawable(R.drawable.asteroide1);
            drawableShip = context.getResources().getDrawable(R.drawable.nave);
            drawableMissile = context.getResources().getDrawable(R.drawable.misil1);
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
        missile = new AsteroidsGraphic(this, drawableMissile);

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        activateSensors();

        soundPool = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        idFire = soundPool.load(context, R.raw.dispar, 0);
        idExplosion = soundPool.load(context, R.raw.explosio, 0);

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
        prevUpdate = System.currentTimeMillis();
        thread.start();
    }
    @Override protected synchronized void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        for (AsteroidsGraphic asteroid: asteroids) {
            asteroid.drawGraphic(canvas);
        }
        ship.drawGraphic(canvas);
        for (AsteroidsGraphic missile : missiles) {
            missile.drawGraphic(canvas);
        }

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
// Processam la pulsació
        boolean processed = true;
        if(controls.equals("0")){

            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_UP:
                    accelShip = 0;
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    ship.setRotSpeed(0);
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    ship.setRotSpeed(0);
                    break;
                case KeyEvent.KEYCODE_DPAD_CENTER:


                case KeyEvent.KEYCODE_ENTER:
                    fireMissile();
                    break;
                default:
// Si estem aquí, no hi ha pulsació que ens interessi
                    processed = false;
                    break;
            }
        }
        return processed;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
// Processam la pulsació
        boolean processed = true;
        if(controls.equals("0")){

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                accelShip = +STEPSIZE_ACCEL_SHIP;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                ship.setRotSpeed(-STEPSIZE_ROT_SHIP);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                ship.setRotSpeed(STEPSIZE_ROT_SHIP);
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:


            case KeyEvent.KEYCODE_ENTER:
                fireMissile();
                break;
            default:
// Si estem aquí, no hi ha pulsació que ens interessi
                processed = false;
                break;
        }  }

        return processed;
    }

    protected synchronized void updateView(){
        long now = System.currentTimeMillis();
// No fer res fins a final del període
        if (prevUpdate + ANIM_INTERVAL > now){
            return;
        }
// Per a una execució en temps real calculam retard
        double delay = (now - prevUpdate) / ANIM_INTERVAL;



        prevUpdate = now;
// Actualitzam velocitat i direcció de la nau a partir de
// ship.rotAngle, ship.rotSpeed, and accelShip
        ship.setRotAngle((int) (ship.getRotAngle() + ship.getRotSpeed()
                * delay));
        double nIncX = ship.getIncX() + accelShip *
                Math.cos(Math.toRadians(ship.getRotAngle())) * delay;
        double nIncY = ship.getIncY() + accelShip *
                Math.sin(Math.toRadians(ship.getRotAngle())) * delay;
// Actualitzam si el mòdul de la velocitat no excedeix el màxim
        if (Math.hypot(nIncX,nIncY) <= SHIP_MAX_SPEED){
            ship.setIncX(nIncX);
            ship.setIncY(nIncY);
        }
// Actualitzam posicions X i Y
        ship.updatePos(delay);
        for (AsteroidsGraphic asteroid : asteroids) {
            asteroid.updatePos(delay);
        }

       /* if (missileActive) {
            missile.updatePos(delay);
            missileLifetime-=delay;
            if (missileLifetime < 0) {
                missileActive = false;
            } else {
                for (int i = 0; i < asteroids.size(); i++)
                    if (missile.checkCollision(asteroids.get(i))) {
                        destroyAsteroid(i);
                        break;
                    }
            }
        }*/

        for (int i = 0; i < missiles.size(); i++) {
            AsteroidsGraphic missile = missiles.get(i);
            missile.updatePos(delay);
            missileLifetimes.set(i, missileLifetimes.get(i) - delay);

            if (missileLifetimes.get(i) < 0) {
                missiles.remove(i);
                missileLifetimes.remove(i);
                i--;
            } else {
                for (int j = 0; j < asteroids.size(); j++) {
                    if (missile.checkCollision(asteroids.get(j))) {
                        destroyAsteroid(j);
                        missiles.remove(i);
                        missileLifetimes.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(controls.equals("2")){
            float value = sensorEvent.values[1];
            if (!initValueValid){
                initValue = value;
                initValueValid = true;
            }
            //accelShip = +STEPSIZE_ACCEL_SHIP;
            ship.setRotSpeed((int) (value-initValue)/3) ;
            accelShip = sensorEvent.values[2] / 20;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void destroyAsteroid(int i) {
        asteroids.remove(i);
        soundPool.play(idExplosion, 1, 1, 0, 0, 1);
    }
    private void fireMissile() {
        AsteroidsGraphic newMissile = new AsteroidsGraphic(this, drawableMissile);
        newMissile.setCenX(ship.getCenX());
        newMissile.setCenY(ship.getCenY());
        newMissile.setRotAngle(ship.getRotAngle());
        newMissile.setIncX(Math.cos(Math.toRadians(newMissile.getRotAngle())) * MISSILE_SPEED);
        newMissile.setIncY(Math.sin(Math.toRadians(newMissile.getRotAngle())) * MISSILE_SPEED);

        missiles.add(newMissile);  // Afegim el nou míssil a la llista
        missileLifetimes.add((double) Math.min(this.getWidth() / Math.abs(newMissile.getIncX()), this.getHeight() / Math.abs(newMissile.getIncY())) - 2);  // Afegim el temps de vida
        soundPool.play(idFire, 1, 1, 1, 0, 1);
    }

    public void activateSensors(){
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (!sensorList.isEmpty()) {
            Sensor accelerometerSensor = sensorList.get(0);
            mSensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }
    public void deactivateSensors(){
        mSensorManager.unregisterListener(this);
    }


}