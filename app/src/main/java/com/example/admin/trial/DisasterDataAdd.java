package com.example.admin.trial;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DisasterDataAdd extends AppCompatActivity {
    FirebaseFirestore firestore;
    EditText Id,Name,Type,Death,Impact,Injuries,State,Year,Latitude,Longitude;
    Button Submit;
    String id,name,type,injres,state,yr,s1;
    int death=0,impact=0;
    Double lat,longi;
    int countt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disaster_data_add);
        firestore = FirebaseFirestore.getInstance();
        Id = findViewById(R.id.Idtext);
        Name = findViewById(R.id.Nametext);
        Type = findViewById(R.id.Typetext);
        Death = findViewById(R.id.Deathtext);
        Impact = findViewById(R.id.Impacttext);
        Injuries = findViewById(R.id.Injuriestext);
        State = findViewById(R.id.Statetext);
        Year = findViewById(R.id.Yeartext);
        Latitude = findViewById(R.id.Latitudetext);
        Longitude = findViewById(R.id.Longitudetext);
        Submit = findViewById(R.id.Submit);

        Intent myintent = this.getIntent();
        s1 = myintent.getStringExtra("count");
        System.out.println("String value : "+s1);

        /*Bundle bundle = getIntent().getExtras();
        if( bundle != null) {
            s1 = bundle.getString("count");
            //s1 = getIntent().getStringExtra("count");
            System.out.println("String Value : "+s1);
        }*/
        countt = Integer.parseInt(s1);
        //countt = 2;
        System.out.println("Disaster ka "+countt);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = Id.getText().toString();
                name = Name.getText().toString();
                type = Type.getText().toString();
                death = Integer.parseInt(Death.getText().toString());
                impact = Integer.parseInt(Impact.getText().toString());
                injres = Injuries.getText().toString();
                state = State.getText().toString();
                yr = Year.getText().toString();
                lat = Double.parseDouble(Latitude.getText().toString());
                longi = Double.parseDouble(Longitude.getText().toString());
                if(id!=null && name!=null && type!=null && death!=0 && impact!=0 && injres!=null && state!=null && yr!=null && lat!=null && longi!=null ) {
                    addData();
                    Id.setText(null);
                    Name.setText(null);
                    Type.setText(null);
                    Death.setText(null);
                    Impact.setText(null);
                    Injuries.setText(null);
                    State.setText(null);
                    Year.setText(null);
                    Latitude.setText(null);
                    Longitude.setText(null);
                }
                else{
                    Toast.makeText(DisasterDataAdd.this, "Field Cant be Empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addData() {
        Map<String,Object>newData = new HashMap<>();
        newData.put("Disaster name",name);
        newData.put("Type",type);
        newData.put("Death toll", death);
        newData.put("Impact",impact);
        newData.put("Impact and injuries",injres);
        newData.put("State",state);
        newData.put("Year",yr);
        newData.put("Latitude",lat);
        newData.put("Longitude",longi);
        firestore.collection("Disaster").document(id).set(newData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(DisasterDataAdd.this,"Succesfully Added!",Toast.LENGTH_SHORT).show();
                countt++;
                updatecount(countt);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DisasterDataAdd.this,"Error :"+e.toString(),Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void updatecount(final int count1) {

        DocumentReference cntupdate = firestore.collection("Disaster").document("iQg3saZVKU8rOHKhglNI");
        cntupdate.update("count",count1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Incremented Count : "+count1);
                Toast.makeText(DisasterDataAdd.this,"Count Incremented!!",Toast.LENGTH_SHORT).show();
            }
        });
        /*Map<String,Object> countinc = new HashMap<>();
        countinc.put("count",count1);
        firestore.collection("Disaster").document("iQg3saZVKU8rOHKhglNI").set(countinc).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Incremented Count : "+count1);
                Toast.makeText(DisasterDataAdd.this,"Count Incremented!!",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DisasterDataAdd.this,"Error :"+e.toString(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
