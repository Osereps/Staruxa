package com.example.app21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit.*;
import com.yandex.mapkit.*;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Cluster;
import com.yandex.mapkit.map.ClusterListener;
import com.yandex.mapkit.mapview.MapView;

import java.sql.DriverPropertyInfo;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapview;
    private Button btn1,btn2;
    private MapKit mapkit;

    private  List<Point> lst1;
    private  List<Point> Pint= new ArrayList<>();
    private Point TARGET_LOCATION = new Point(59.945933, 30.320045);
    private Point TARGET_LOCATION1 = new Point(55.751574, 37.573856);
    private Point TARGET_LOCATION2 = new Point(50.751574, 35.573856);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("56f5b8b5-24fa-4009-97b1-39095ee6c0ca");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main);
        mapkit = MapKitFactory.getInstance();
        mapview = findViewById(R.id.mapview);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        mapview.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 2),
                null);
        mapview.getMap().getMapObjects().addPlacemark(TARGET_LOCATION);
        mapview.getMap().getMapObjects().addPlacemark(TARGET_LOCATION1);

        lst1 = new ArrayList<>();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapkit.createLocationManager().requestSingleUpdate(new LocationListener() {
                    @Override
                    public void onLocationUpdated(@NonNull Location location) {
                        mapview.getMap().move(
                                new CameraPosition(location.getPosition(), 18.0f, 0.0f, 0.0f),
                                new Animation(Animation.Type.SMOOTH, 2),
                                null);
                        Point pp = location.getPosition();
                        lst1.add(pp);
                        mapview.getMap().getMapObjects().addPlacemark(location.getPosition());

                    }

                    @Override
                    public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {
                    }
                });
            }
        });
        mapview.getMap().addTapListener(new GeoObjectTapListener() {
            @Override
            public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
                Log.d("Main",geoObjectTapEvent.getGeoObject().getName());
                return false;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Point> lst = new ArrayList<>();
                lst.add(TARGET_LOCATION);
                lst.add(TARGET_LOCATION1);
                lst1.add(TARGET_LOCATION2);

                mapview.getMap().getMapObjects().addPolyline(new Polyline(lst));
                mapview.getMap().getMapObjects().addPolyline(new Polyline(lst1));

                Toast.makeText(MainActivity.this, "Нафиг всё это", Toast.LENGTH_SHORT).show();

                Pint.add(new Point(21,20));
                Pint.add(new Point(23,25));
                Pint.add(new Point(25,20));
                Pint.add(new Point(20,23));
                Pint.add(new Point(26,23));
                Pint.add(new Point(21,20));
                mapview.getMap().getMapObjects().addPolyline(new Polyline(Pint));
            }
        });

    }
    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
}