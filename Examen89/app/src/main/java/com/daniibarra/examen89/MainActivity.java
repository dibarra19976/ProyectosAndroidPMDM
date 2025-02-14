package com.daniibarra.examen89;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //instancia del reciever
    ReceiverDistancia rd = new ReceiverDistancia();
    //objeto mapa  donde pondremos el objeto mapa cuando haya cargado mas tarde
    GoogleMap map = null;

    //variables para las notificaciones u manejador de las notificaciones
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager notifyMan;

    //posicion del ramis, para establecer la camara cuando carga el mapa
    private final LatLng ramis = new LatLng(29.8875,4.2548);

    //longitud previa, para comparar dodne se encuentra el usuario en el cameraMove
    //por defecto es la misma latitud del ramis
    double prev_lat = 29.8875;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // se establece que donde se mostrara el mapa ser aen el fragmento con la id mapa del layout principal
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        //se carga de manera asincrona el mapa
        mapFragment.getMapAsync(this);

        IntentFilter i = new IntentFilter();
        //i.addAction(Intent.NEW_MARKER);

        //se establece el filtro y se registra el broadcastreciever
        this.registerReceiver(rd, i);

        //se crea el canal de las notificaciones
        createChannel();
    }

    //elimina el reciever cuando se cierra la actividad
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(rd);
    }

    //metodo que implementa OnMapReadyCallback
    // se llama cuando termina de cargarse el mapa con getmapAsync
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //establecemos el mapa en nuestra variable
        //como esta asignaada al fragment se mostrara
        map = googleMap;
        //el tipo de mapa que se muestra
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //la direccion donde se encoca el mapa es el ramis
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ramis, 15));

        //eventlistener para cuando se hace click
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                //añadimos un marcador con el latLng de donde se pulso
                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Posicion nueva")
                );

                //este codigo deberia de ir en receiverDistancia
                //se recogen las dos latitud y longitudes (ramis y marcador nuevo
                LatLng latlng1 = ramis;
                LatLng latlng2 = latLng;
                //se crean los location y se añaden las propiedades de las latitud y longitud
                Location direccion1 = new Location("Primera direccion");
                Location direccion2 = new Location("Segunda direccion");
                direccion1.setLatitude(latlng1.latitude);
                direccion1.setLongitude(latlng1.longitude);
                direccion2.setLatitude(latlng2.latitude);
                direccion2.setLongitude(latlng2.longitude);

                //se compara la distancia
                double distancia = direccion1.distanceTo(direccion2);

                //se genera un toat por pantalla
                Toast.makeText(MainActivity.this, "Distancia entre el ramis y el punto nuevo: " + distancia, Toast.LENGTH_SHORT).show();
            }
        });

        //eventlistener para cuando se mueve la camara
        map.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLng current = map.getCameraPosition().target;

                double current_lat = current.latitude;

                if(prev_lat < 0 && current_lat>0){
                    sendNotification("Norte");
                } else if (prev_lat > 0 && current_lat<0) {
                    sendNotification("Sur");
                }

                prev_lat = current_lat;
            }
        });
    }

    //metodo para crear el canal de las notificaciones
    public void createChannel(){
        notifyMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Movimiento de hemisferio", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setDescription("Notificacion para cuando el usuario se mueve hacia otro hemisferio dentro de la aplicacion");
            notifyMan.createNotificationChannel(notificationChannel);}
    }


    //metodo para generar el builder de la notificacion
    //se le pasa el hemisferio para que el mensaje cambie dependiendo de la posicion
    private NotificationCompat.Builder getBuilder(String hemisferio){
        return new
                NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Te has movido")
                .setContentText("Te encuentras en el hemisferio  " + hemisferio + "!")
                .setSmallIcon(R.drawable.baseline_map_24);
    }

    //metodo para mandar una notificaion
    //se le pasa el hemisferio para que lo añada al builder (lo pasa para la creacion en su propio metodo)
    public void sendNotification(String hemisferio) {
        NotificationCompat.Builder notifyBuilder = getBuilder(hemisferio);
        notifyMan.notify(NOTIFICATION_ID, notifyBuilder.build());
    }
}