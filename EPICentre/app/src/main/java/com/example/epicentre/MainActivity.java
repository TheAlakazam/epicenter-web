package com.example.epicentre;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback{

    Button updateButton;
    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    static LatLng Current;
    RequestInterface requestInterface;
    String BaseURL="https://proapplication.000webhostapp.com/AngelHacks/";
    ArrayList<Data> datas;
    private GoogleMap mMap;

    //String BaseURL="http://192.168.137.1/AngelHack/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLocationPermission();
        datas= new Items(this).Populate();
        RecyclerView recyclerView= findViewById(R.id.InformationView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(datas, this));



        updateButton=findViewById(R.id.updateButton);
        requestInterface= new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestInterface.class);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateLocation();

            }
        });
        initMap();
    }

    public void updateLocation()
    {


        LocationJSON myLocation= new LocationJSON(Current.latitude,Current.longitude);

        // Calling Async Retrofit

        Call<ResponseJSON> call= requestInterface.SendLocation(myLocation);
        call.enqueue(new Callback<ResponseJSON>() {
            @Override
            public void onResponse(Call<ResponseJSON> call, Response<ResponseJSON> response) {
                String resposeMessage=response.body().getLocation();
                Toast.makeText(MainActivity.this, resposeMessage, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseJSON> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Get Permission For Location
    private void getLocationPermission(){

        Log.d(TAG, "getLocationPermission: getting location permissions");

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,

                Manifest.permission.ACCESS_COARSE_LOCATION};



        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),

                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),

                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                mLocationPermissionsGranted = true;



            }else{

                ActivityCompat.requestPermissions(this,

                        permissions,

                        LOCATION_PERMISSION_REQUEST_CODE);

            }

        }else{

            ActivityCompat.requestPermissions(this,

                    permissions,

                    LOCATION_PERMISSION_REQUEST_CODE);

        }

    }
    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG, "onRequestPermissionsResult: called.");

        mLocationPermissionsGranted = false;



        switch(requestCode){

            case LOCATION_PERMISSION_REQUEST_CODE:{

                if(grantResults.length > 0){

                    for(int i = 0; i < grantResults.length; i++){

                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){

                            mLocationPermissionsGranted = false;

                            Log.d(TAG, "onRequestPermissionsResult: permission failed");

                            return;

                        }

                    }

                    Log.d(TAG, "onRequestPermissionsResult: permission granted");

                    mLocationPermissionsGranted = true;

                    //initialize our map



                }

            }

        }

    }

    //Getting Device Location
    private void getDeviceLocation(){

        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {

                    @Override

                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){

                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                            String dummy="My location";
                            Current= new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),15f);

                        }else{

                            Log.d(TAG, "onComplete: current location is null");

                            Toast.makeText(MainActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,

                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;

            }
            mMap.setMyLocationEnabled(true);

        }
    }

    private void initMap(){

        Log.d(TAG, "initMap: initializing map");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MainActivity.this);

    }



    //Move camera
    private void moveCamera(final LatLng latLng, float zoom){

        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

    }
}
