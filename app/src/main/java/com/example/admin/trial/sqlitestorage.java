package com.example.admin.trial;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class sqlitestorage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseFirestore db;
    Button Analysis,Map,show,adddata;
    ImageView img1,img2;
    SQLiteDatabase SQLDB;
    int i, Imp;
    Double l1, l2;
    String s1,s2;
    String name,type,state,year,injuries,text;
    int count,death;
    Spinner typeselect;
    private static final String Fire_Log = "Fire_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        db = FirebaseFirestore.getInstance();
        //Analysis = (Button) findViewById(R.id.Calculate);
        //Map = (Button) findViewById(R.id.MapAnalysis);
        show = (Button) findViewById(R.id.show);
        typeselect = (Spinner) findViewById(R.id.typeselect);
        adddata = (Button) findViewById(R.id.Adddata);
        img1 =(ImageView) findViewById(R.id.image1);
        img2 = (ImageView)findViewById(R.id.image2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeselect.setAdapter(adapter);
        typeselect.setOnItemSelectedListener(this);


        s1 = getIntent().getStringExtra("count");
        count = Integer.parseInt(s1);
        System.out.println("Analysis ka "+count);


        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(sqlitestorage.this,DisasterDataAdd.class);
                if(count!=0)
                {
                    s2 = Integer.toString(count);
                    /*Bundle bundle = new Bundle();
                    bundle.putString("Count",s2);
                    add.putExtras(bundle);*/
                    add.putExtra("count",s2);
                    startActivity(add);
                }
                else{
                    Toast.makeText(sqlitestorage.this,"Count cant be zero",Toast.LENGTH_SHORT).show();
                }

            }
        });

       /* Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sqlitestorage.this,MapActivity.class);
                //s1 = Integer.toString(count);
                //intent.putExtra("count",s1);
                intent.putExtra("count",count);
                intent.putExtra("type",text);
                startActivity(intent);
            }
        });*/

      /*  Analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLDB = openOrCreateDatabase("Firestore", 0, null);
                SQLDB.execSQL("drop table if exists Geolocation;");
                SQLDB.execSQL("create table Geolocation(name varchar(40),type varchar(40),state varchar(50),year varchar(30),deathtoll int(40),injuries varchar(50),latitude double(30), longitude double(30), impact int(40));");
                for (i = 1; i <= count; i++) {
                    DocumentReference Location = db.collection("Disaster").document(Integer.toString(i));
                    Location.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot Doc = task.getResult();
                            if (Doc.exists() && Doc != null) {
                                name = Doc.getString("Disaster name");
                                type = Doc.getString("Type");
                                state = Doc.getString("State");
                                year = Doc.getString("Year");
                                death = Doc.getLong("Death toll").intValue();
                                //death = Integer.parseInt(Doc.getString("Death toll"));
                                injuries = Doc.getString("Impact and injuries");
                                l1 = Doc.getDouble("Latitude");
                                l2 = Doc.getDouble("Longitude");
                                Imp = Doc.getLong("Impact").intValue();
                                System.out.println("!!!!!!!!!!!@@@" + name +"\t"+ type +"\t"+ state +"\t"+ year +"\t"+ death +"\t"+ injuries +"\t"+ l1 + "\t" + l2 + "\t" + Imp);
                                SQLDB.execSQL("insert into Geolocation values(\"" + name + "\",\"" + type + "\",\"" + state + "\",\"" + year + "\",\"" + death + "\",\"" + injuries + "\",\"" + l1 + "\",\"" + l2 + "\",\"" + Imp + "\");");
                                //i++;

                            }
                        }
                    });
                }
                Analysis.setVisibility(View.GONE);
                Map.setVisibility(View.VISIBLE);
                show.setVisibility(View.VISIBLE);
                adddata.setVisibility(View.VISIBLE);
            }
        });*/

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sqlitestorage.this,viewdata.class);
                intent.putExtra("count",count);
                intent.putExtra("type",text);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
        if(text == "select one")
        {
            Map.setVisibility(View.GONE);
            show.setVisibility(View.GONE);
            adddata.setVisibility(View.GONE);
        }
        else
        {
            function(text);
            System.out.println("sqlite wala :" + text);
        }
    }

    private void function(String text) {
        SQLDB = openOrCreateDatabase("Firestore", 0, null);
        SQLDB.execSQL("drop table if exists Geolocation;");
        SQLDB.execSQL("create table Geolocation(name varchar(40),type varchar(40),state varchar(50),year varchar(30),deathtoll int(40),injuries varchar(50),latitude double(30), longitude double(30), impact int(40));");
        for (i = 1; i <= count; i++) {
            DocumentReference Location = db.collection("Disaster").document(Integer.toString(i));
            Location.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot Doc = task.getResult();
                    if (Doc.exists() && Doc != null) {
                        name = Doc.getString("Disaster name");
                        type = Doc.getString("Type");
                        state = Doc.getString("State");
                        year = Doc.getString("Year");
                        death = Doc.getLong("Death toll").intValue();
                        //death = Integer.parseInt(Doc.getString("Death toll"));
                        injuries = Doc.getString("Impact and injuries");
                        l1 = Doc.getDouble("Latitude");
                        l2 = Doc.getDouble("Longitude");
                        Imp = Doc.getLong("Impact").intValue();
                        System.out.println("!!!!!!!!!!!@@@" + name +"\t"+ type +"\t"+ state +"\t"+ year +"\t"+ death +"\t"+ injuries +"\t"+ l1 + "\t" + l2 + "\t" + Imp);
                        SQLDB.execSQL("insert into Geolocation values(\"" + name + "\",\"" + type + "\",\"" + state + "\",\"" + year + "\",\"" + death + "\",\"" + injuries + "\",\"" + l1 + "\",\"" + l2 + "\",\"" + Imp + "\");");
                        //i++;

                    }
                }
            });
        }
//        Analysis.setVisibility(View.GONE);
//        Map.setVisibility(View.VISIBLE);
        show.setVisibility(View.VISIBLE);
        adddata.setVisibility(View.VISIBLE);
        img1.setVisibility(View.VISIBLE);
        img2.setVisibility(View.VISIBLE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

