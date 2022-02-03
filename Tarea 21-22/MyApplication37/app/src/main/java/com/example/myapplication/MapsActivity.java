package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button b1, b2, b3, b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        b1 = (Button)findViewById(R.id.xbn1);
        b2 = (Button)findViewById(R.id.xbn2);
        b3 = (Button)findViewById(R.id.xbn3);
        b4 = (Button)findViewById(R.id.xbn4);

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                Context contexto = getApplicationContext();
                String msg = "Posición: " + mMap.getCameraPosition().target;
                Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMap.getMapType() != GoogleMap.MAP_TYPE_SATELLITE)
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng sydney = new LatLng(19.4978, -99.1269);
                mMap.addMarker(new MarkerOptions().position(new LatLng(19.4978, -99.1269)).title("México")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .snippet("Here's " + "Mexico"))
                        .showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 3.0f));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double latitud = 37.40*1E6;
                Double longitud = -5.99*1E6;
                LatLng sydney = new LatLng(37.3826, -5.99629);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sevilla"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng sydney = new LatLng(19.4978, -99.1269);
                mMap.addMarker(new MarkerOptions().position(new LatLng(19.4978, -99.1269)).title("México")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .snippet("Here's " + "Mexico"))
                        .showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 3.0f));
            }
        });
    }
}