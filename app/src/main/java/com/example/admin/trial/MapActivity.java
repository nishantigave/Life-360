package com.example.admin.trial;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String TAG = "MapActivity";
    Double l1, l2;
    int Imp;
    FirebaseFirestore db;

    LatLng centre;
    SQLiteDatabase SQLDB;
    int count ;
    int i;
    String type;
    private static final String Fire_Log = "Fire_Log";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //l1 = getIntent().getStringExtra("Latitude");
        //l2 = getIntent().getStringExtra("Longitude");
        //type = getIntent().getStringExtra("type");
        //System.out.println("Map type "+type);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {


        mMap = googleMap;
        centre = new LatLng(23.8913, 79.8792);



        SQLDB = openOrCreateDatabase("Firestore",0,null);
        SQLDB.execSQL("create table if not exists Geolocation (name varchar(40),type varchar(40),state varchar(50),year varchar(30),deathtoll int(20),injuries varchar(50),latitude double(30), longitude double(30), impact int(40));");
        Cursor GetTableEntry = SQLDB.rawQuery("select latitude , longitude , impact from Geolocation ;",null);
        count = GetTableEntry.getCount();
        System.out.println("Count :i----"+count);
        List<CityName> A= new ArrayList<>();
        GetTableEntry.moveToFirst();
        for (i=0;i<count;i++) {
            l1 = GetTableEntry.getDouble(0);
            l2 = GetTableEntry.getDouble(1);
            Imp = GetTableEntry.getInt(2);
            System.out.println(l1 + "\t" + l2 + "\t" + Imp);
            A.add(new CityName(l1, l2, Imp));
            GetTableEntry.moveToNext();
        }

        for(int j=0;j<count;j++) {
            mMap.addCircle(new CircleOptions().center(A.get(j).getCity())
                    .radius(A.get(j).getImpact())
                    .strokeColor(Color.argb(100, 255, 100, 100))
                    .fillColor(Color.RED));
            if(j==count-1) {
                CameraPosition cameraPosition = new CameraPosition.Builder().target(centre).zoom(4).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                mMap.moveCamera(cameraUpdate);
            }

        }



    }
}
 /*db.collection("Disaster").document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists() && documentSnapshot != null) {

                        System.out.println(l1 + "\t" + l2 + "\t" + Imp);
                        Toast.makeText(MapActivity.this, "SuccessFully Done!", Toast.LENGTH_SHORT).show();*/


//System.out.println("Impact = "+Obj.getImpact());