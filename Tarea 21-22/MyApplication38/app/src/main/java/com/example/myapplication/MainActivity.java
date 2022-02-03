package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//import android.support.v7.app.AppCompatActivity;

/**
 * This shows how to create a simple activity with a raw MapView and add a marker to it. This
 * requires forwarding all the important lifecycle methods onto MapView.
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mMapView;
    private Button b1, b2, b3, b4;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);

        b1 = (Button)findViewById(R.id.xbn1);
        b2 = (Button)findViewById(R.id.xbn2);
        b3 = (Button)findViewById(R.id.xbn3);
        b4 = (Button)findViewById(R.id.xbn4);



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map.getMapType()==GoogleMap.MAP_TYPE_SATELLITE){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }else{
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng sydney = new LatLng(19.4978, -99.1269);
                map.addMarker(new MarkerOptions().position(new LatLng(19.4978, -99.1269)).title("México")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .snippet("Here's " + "Mexico"))
                        .showInfoWindow();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 3.0f));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double latitud = 37.40*1E6;
                Double longitud = -5.99*1E6;
                LatLng Sevilla = new LatLng(latitud, -longitud);
                map.addMarker(new MarkerOptions().position(Sevilla).title("Marker in Sevilla"));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(Sevilla, 12.0f));
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double latitud = 37.40*1E6;
                Double longitud = -5.99*1E6;
                LatLng Sevilla = new LatLng(latitud, -longitud);
                map.addMarker(new MarkerOptions().position(Sevilla).title("Marker in Sevilla"));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(Sevilla, 12.0f));
            }
        });
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}