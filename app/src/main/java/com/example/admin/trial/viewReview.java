package com.example.admin.trial;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class viewReview extends AppCompatActivity {

    String s1,s2,x;
    int count,i;
    SQLiteDatabase SQLDB;
    RecyclerView R1;
    ReviewAdapter D1;
    List<Reviewextract> DL1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);
        R1 = (RecyclerView) findViewById(R.id.recylcerview1);
        R1.setLayoutManager(new LinearLayoutManager(this));
        x= getIntent().getStringExtra("count");
        int counter = Integer.parseInt(x);
        System.out.println("Value of X : "+x);

        DL1 = new ArrayList<>();

        SQLDB = openOrCreateDatabase("Firestore",0,null);
        SQLDB.execSQL("create table if not exists Review(username varchar(40),review varchar(500));");
        Cursor GetTableEntry = SQLDB.rawQuery("select *  from  Review;",null);
        count = GetTableEntry.getCount();
        System.out.println("Count :i----"+count);
        //List<CityName> A= new ArrayList<>();
        GetTableEntry.moveToFirst();
        for (i=0;i<count;i++) {
            s1 = GetTableEntry.getString(0).toString();
            s2 = GetTableEntry.getString(1).toString();
            System.out.println("Strings are :"+s1+"\t"+s2);
            DL1.add(new Reviewextract(s1,s2));
            GetTableEntry.moveToNext();
        }

        D1 = new ReviewAdapter(this, DL1);

        R1.setAdapter(D1);


    }
}
