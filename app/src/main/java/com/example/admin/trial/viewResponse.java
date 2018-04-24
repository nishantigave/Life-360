package com.example.admin.trial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class viewResponse extends AppCompatActivity {
    String s1,s2,s3,x;
    int count,i;
    SQLiteDatabase SQLDB;
    RecyclerView R1;
    ResponseAdapter D1;
    List<Responseextract> DL1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_response);
        R1 = (RecyclerView) findViewById(R.id.recylcerview1);
        R1.setLayoutManager(new LinearLayoutManager(this));
        x= getIntent().getStringExtra("count");
        int counter = Integer.parseInt(x);
        System.out.println("Value of X : "+x);

        DL1 = new ArrayList<>();


        SQLDB = openOrCreateDatabase("Firestore",0,null);
        SQLDB.execSQL("create table if not exists Response(name varchar(40),response varchar(300),responsers varchar(100));");
        Cursor GetTableEntry = SQLDB.rawQuery("select *  from Response ;",null);
        count = GetTableEntry.getCount();
        System.out.println("Count :i----"+count);
        //List<CityName> A= new ArrayList<>();
        GetTableEntry.moveToFirst();
        for (i=0;i<counter;i++) {
            s1 = GetTableEntry.getString(0).toString();
            s2 = GetTableEntry.getString(1).toString();
            s3 = GetTableEntry.getString(2).toString();
            System.out.println("Strings are :"+s1+"\t"+s2+"\t"+s3);
            DL1.add(new Responseextract(s1,s2,s3));
            GetTableEntry.moveToNext();
        }

        D1 = new ResponseAdapter(this, DL1);

        R1.setAdapter(D1);

    }
}
