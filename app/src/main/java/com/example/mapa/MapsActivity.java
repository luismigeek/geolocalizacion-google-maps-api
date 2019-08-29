package com.example.mapa;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
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
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Spinner spn;

    /**
     * CORDENADAS GEOGRAFICAS DE 5 LUGARES DISTINTOS
     */
    private static final LatLng location1 = new LatLng(8.758806, -75.883621);
    private static final LatLng location2 = new LatLng(8.761309, -75.879909);
    private static final LatLng location3 = new LatLng(8.754427, -75.886499);

    /**
     * MARCADORES GEOGRAFICOS PARA UBICACIONES
     */
    private Marker m0;
    private Marker m1;
    private Marker m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getLocationPermission();

        spn = findViewById(R.id.immovables_spn);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.immovables, R.layout.customspinner);

        // Apply the adapter to the spinner
        spn.setAdapter(adapter);

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
        mMap.setOnInfoWindowClickListener(this);

        mMap.setMyLocationEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }

    private void addMarkers() {

        BitmapDescriptor icon1 = BitmapDescriptorFactory.fromResource(R.drawable.homeaddress50px);
        BitmapDescriptor icon2 = BitmapDescriptorFactory.fromResource(R.drawable.placeholder50px);

        m0 = mMap.addMarker(new MarkerOptions()
                .position(location1)
                .title("APARTAESTUDIO 301 EDIFICIO ÁREA 62 CASTELLANA")
                .snippet("$780,000.00/month, calle 62 #8-48, Montería, Córdoba, Colombia")
                .icon(icon2)
        );
        m1 = mMap.addMarker(new MarkerOptions()
                .position(location2)
                .title("CASA CARRERA 13 #53-75 URB. MONTEVERDE PARA VENTA")
                .snippet("Call for price, carrera 13 #53-75, Montería, Córdoba, Colombia")
                .icon(icon2)
        );
        m2 = mMap.addMarker(new MarkerOptions()
                .position(location3)
                .title("CASA 1 URB. VALLEJO II PARA LA VENTA")
                .snippet("Call for price, carrera 15 #19A - 48W Montería, Córdoba, Colombia")
                .icon(icon2)
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 15));

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, CualquierCosita.class);
        String markerid = marker.getId();
        intent.putExtra(EXTRA_MESSAGE, markerid);
        startActivity(intent);
    }

    public void Buscar(View v) {
        int select = spn.getSelectedItemPosition();
        Toast.makeText(this, "Buscando " + spn.getSelectedItem().toString() + " en el mapa", Toast.LENGTH_SHORT).show();

        switch (select) {
            case 0:
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 20));
                m0.showInfoWindow();
                break;
            case 1:
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location2, 20));
                m1.showInfoWindow();
                break;
            case 2:
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location3, 20));
                m2.showInfoWindow();
                break;
            default:
                Toast.makeText(this, "Buscando " + select + " en el mapa", Toast.LENGTH_SHORT).show();
        }
    }


}

