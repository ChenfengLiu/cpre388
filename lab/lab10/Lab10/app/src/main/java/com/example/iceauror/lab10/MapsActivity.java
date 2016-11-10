package com.example.iceauror.lab10;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private double distance;
    private double area;
    private double dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // TODO Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        distance = 0.0;
        area = 0.0;
        dir = 0.0;

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // TODO Add a marker in your hometown
        LatLng Ames = new LatLng(42.034534, -93.620369);
        LatLng MountainView = new LatLng(37.423, -122.091);
        mMap.addMarker(new MarkerOptions().position(Ames).title("Marker in Ames"));


        LatLng Florida = new LatLng(27.6648, -81.5158);
        LatLng PuertoRico = new LatLng(18.2208, -66.5901);
        LatLng Bermuda = new LatLng(32.3078, -64.7505);

        // TODO Move the camera to the Marker Location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Ames));

        mMap.addPolyline(new PolylineOptions().geodesic(true).add(Ames).add(MountainView));

        mMap.addPolygon(new PolygonOptions().add(Florida).add(PuertoRico).add(Bermuda));

        // TODO Change the initial states of the Map.
        //see in activity_maps.xml

        // TODO Add the styling to the Map
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_night));

        //Calculate distance from Ames to Mountain View
        ArrayList<LatLng> p1 = new ArrayList<>();
        p1.add(Ames);
        p1.add(MountainView);
        calDist(p1);

        ArrayList<LatLng> p2 = new ArrayList<>();
        p2.add(Florida);
        p2.add(PuertoRico);
        p2.add(Bermuda);
        calArea(p2);

        ArrayList<LatLng> p3 = new ArrayList<>();
        p3.add(Florida);
        p3.add(PuertoRico);
        calDir(p3);
        //add text to map
        mMap.addMarker(new MarkerOptions().position(MountainView).title("Distance is: " + distance + " meters."));
        mMap.addMarker(new MarkerOptions().position(PuertoRico).title("Distance from Florida to here is: " + dir + " meters"));
        mMap.addMarker(new MarkerOptions().position(Florida).title("Area is: " + area + " meters^2"));
    }

    private void calDist(ArrayList<LatLng> path){
        distance = SphericalUtil.computeLength(path);
    }

    private void calArea(ArrayList<LatLng> path){
        area = SphericalUtil.computeArea(path);
    }

    private void calDir(ArrayList<LatLng> path){
        dir = SphericalUtil.computeHeading(path.get(0), path.get(1));
    }
}