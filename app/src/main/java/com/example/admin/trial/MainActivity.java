package com.example.admin.trial;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOGUE_REQUEST = 9001;
    Double lat,longl;
    private Button button,weather,btnmap,map,adddata;
    private TextView textview;
    private ProgressBar pb;
    private LocationManager locationManager;
    private LocationListener locationListener;
    FirebaseFirestore db;
    int count;
    String s1, s2;
    Double l1,l2;
    int i=0,Imp;
    //SQLiteDatabase SQLDB;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.location);
        textview = (TextView) findViewById(R.id.coordinate);
        btnmap = (Button)findViewById(R.id.fetch);
        map = (Button) findViewById(R.id.map);
        textview.setText(null);
        pb = (ProgressBar) findViewById(R.id.progressbar);
        weather = (Button) findViewById(R.id.weather);
        adddata = (Button)findViewById(R.id.Adddata);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        db = FirebaseFirestore.getInstance();



        if(isServicesOK()){
            init();
        }
       //init();



        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisasterDocument.class);
                if(count!=0) {
                    s1 = Integer.toString(count);
                    //s2 = Double.toString(longl);
                    intent.putExtra("count", s1);
                    //intent.putExtra("Longitude", s2);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Count cant be zero",Toast.LENGTH_SHORT).show();
                }
            }
        });


        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textview.getText().toString().length()>0 ){
                    /*switch(view.getId()) {
                        case R.id.weather:*/

                            Intent intent = new Intent(MainActivity.this, Weather.class);
                            String s1, s2;
                            s1 = Double.toString(lat);
                            s2 = Double.toString(longl);
                            intent.putExtra("Latitude", s1);
                            intent.putExtra("Longitude", s2);
                            startActivity(intent);
                           // break;
                    //}
                }
                else {
                    Toast.makeText(MainActivity.this,"Location Not Found!",Toast.LENGTH_SHORT).show();
                }
            }
        });



        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                longl = location.getLongitude();
                String city = hereLocation(lat,longl);
                textview.append("City :"+city);
                //textview.append("\nLatitude :" + lat + " Longitude : " + longl+" ");
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
            }, 10);
            return;
        } else {
            configureButton();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(view.VISIBLE);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates("gps", 3000000, 5000, locationListener);
            }
        });



    }
    public String hereLocation(double lat,double lon){
        String City="";
        Geocoder geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
        List<Address> addressList;
        try
        {
            addressList=geocoder.getFromLocation(lat,lon,1);
            if (addressList.size()>0)
            {
                City=addressList.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return City;
    }

    private void init(){
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true /*textview.getText().toString().length()>0*/){
                   /* switch(view.getId()){
                        case R.id.button:*/

                   // do {
                        DocumentReference Count_1 = db.collection("Disaster").document("iQg3saZVKU8rOHKhglNI");
                        Count_1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {

                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    if (documentSnapshot.exists() && documentSnapshot != null) {
                                        count = documentSnapshot.getLong("count").intValue();
                                        System.out.println("blah blah  " + count);
                                        Toast.makeText(MainActivity.this, "SuccessFully Done!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    //}while (count==0);

                                            i = 0;

                          //  break;
                    //}

                }
                else {
                    Toast.makeText(MainActivity.this,"Location Not Found!",Toast.LENGTH_SHORT).show();

                }
                btnmap.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        map.setVisibility(View.VISIBLE);
                       // adddata.setVisibility(View.VISIBLE);
                    }
                },3000);

            }
        });

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServicesOK: Google Play Sevices is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServicesOK: an Error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this , available , ERROR_DIALOGUE_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this,"You cant make this request",Toast.LENGTH_SHORT).show();
        }
        return false;
    }


}