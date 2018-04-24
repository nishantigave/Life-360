package com.example.admin.trial;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class viewdata extends AppCompatActivity {
    String type,s1,s2,s3,s4,s5,s6;
    int count,i;
    SQLiteDatabase SQLDB;
    RecyclerView R1;
    DataAdapter D1;
    List<dataextract> DL1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
        R1 = (RecyclerView)findViewById(R.id.recylcerview);
        R1.setLayoutManager(new LinearLayoutManager(this));

        type = getIntent().getStringExtra("type");
        count = getIntent().getIntExtra("count",0);
        System.out.println("viewdata wala count :"+count);
        System.out.println("viewdata wala :"+type);

        DL1 = new ArrayList<>();

        SQLDB = openOrCreateDatabase("Firestore",0,null);
        SQLDB.execSQL("create table if not exists Geolocation (name varchar(40),type varchar(40),state varchar(50),year varchar(30),deathtoll int(20),injuries varchar(50),latitude double(30), longitude double(30), impact int(40));");
        Cursor GetTableEntry = SQLDB.rawQuery("select *  from Geolocation where type =\"" + type + "\";",null);
        count = GetTableEntry.getCount();
        System.out.println("Count :i----"+count);
        //List<CityName> A= new ArrayList<>();
        GetTableEntry.moveToFirst();
        for (i=0;i<count;i++) {
            s1 = GetTableEntry.getString(0).toString();
            s2 = GetTableEntry.getString(1).toString();
            s3 = GetTableEntry.getString(2).toString();
            s4 = GetTableEntry.getString(3).toString();
            s5 = GetTableEntry.getString(4).toString();
            s6 = GetTableEntry.getString(5).toString();
            DL1.add(new dataextract(s1,s2,s3,s4,s5,s6));
            GetTableEntry.moveToNext();
        }




        D1 = new DataAdapter(this, DL1);

        R1.setAdapter(D1);




    }
}
