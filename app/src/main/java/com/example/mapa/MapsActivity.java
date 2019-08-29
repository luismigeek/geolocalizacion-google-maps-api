package com.example.mapa;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    /**
     * CORDENADAS GEOGRAFICAS DE 5 LUGARES DISTINTOS
     */
    private static final LatLng location1 = new LatLng(8.758806, -75.883621);
    private static final LatLng location2 = new LatLng(8.761309, -75.879909);
    private static final LatLng location3 = new LatLng(8.754427, -75.886499);
    private static final LatLng location4 = new LatLng(8.754506, -75.885237);
    private static final LatLng location5 = new LatLng(8.753139, -75.885432);

    /**
     * MARCADORES GEOGRAFICOS PARA UBICACIONES
     */
    private Marker m1;
    private Marker m2;
    private Marker m3;
    private Marker m4;
    private Marker m5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getLocationPermission();
    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        1234);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    1234);
        }
    }

    private void initMap() {
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
        mMap = googleMap;

        addMarkers();

        //Set Custom InfoWindow Adapter

        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapsActivity.this);
        mMap.setInfoWindowAdapter(adapter);

        mMap.setMyLocationEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }

    private void addMarkers() {

        BitmapDescriptor icon1 = BitmapDescriptorFactory.fromResource(R.drawable.homeaddress50px);
        BitmapDescriptor icon2 = BitmapDescriptorFactory.fromResource(R.drawable.placeholder50px);

        m1 = mMap.addMarker(new MarkerOptions()
                .position(location1)
                .title("Inmueble n° 1")
                .snippet("Desc. del inmueble")
                .icon(icon2)
        );
        m2 = mMap.addMarker(new MarkerOptions()
                .position(location2)
                .title("Inmueble n° 2")
                .snippet("Desc. del inmueble")
                .icon(icon2)
        );
        m3 = mMap.addMarker(new MarkerOptions()
                .position(location3)
                .title("Inmueble n° 3")
                .snippet("Desc. del inmueble")
                .icon(icon2)
        );
        m4 = mMap.addMarker(new MarkerOptions()
                .position(location4)
                .title("Inmueble n° 4")
                .snippet("Desc. del inmueble")
                .icon(icon2)
        );
        m5 = mMap.addMarker(new MarkerOptions()
                .position(location5)
                .title("Inmueble n° 5")
                .snippet("Desc. del inmueble")
                .icon(icon2)
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 15));

    }
}
