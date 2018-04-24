package com.example.admin.trial;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LauncherActivity extends AppCompatActivity {

    ImageButton disaster, response, mapplot, viewreview;
    //Button sms;
    TextView a, b, c, d;
    int countdis, countres, countrev;
    ProgressBar progress_verify;
    SQLiteDatabase SQL;
    String a1, a2;
    int pch = 0;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        db = FirebaseFirestore.getInstance();
        progress_verify = (ProgressBar) findViewById(R.id.pbar);
        //sms = (Button) findViewById(R.id.sms);
        disaster = (ImageButton) findViewById(R.id.disasterdoc);
        response = (ImageButton) findViewById(R.id.healthres);
        mapplot = (ImageButton) findViewById(R.id.mapplot);
        viewreview = (ImageButton) findViewById(R.id.viewreview);
        a = (TextView) findViewById(R.id.tv1);
        b = (TextView) findViewById(R.id.tv2);
        c = (TextView) findViewById(R.id.tv3);
        d = (TextView) findViewById(R.id.tv4);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                progress_verify.setVisibility(View.GONE);
                disaster.setVisibility(View.VISIBLE);
                response.setVisibility(View.VISIBLE);
                mapplot.setVisibility(View.VISIBLE);
                viewreview.setVisibility(View.VISIBLE);
                a.setVisibility(View.VISIBLE);
                b.setVisibility(View.VISIBLE);
                c.setVisibility(View.VISIBLE);
                d.setVisibility(View.VISIBLE);

            }
        }, 3000);
        DocumentReference Count_1 = db.collection("Disaster").document("iQg3saZVKU8rOHKhglNI");
        Count_1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists() && documentSnapshot != null) {
                        countdis = documentSnapshot.getLong("count").intValue();
                        System.out.println("blah blah  " + countdis);
                        Toast.makeText(LauncherActivity.this, "SuccessFully Done!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        DocumentReference Count_2 = db.collection("Response").document("j17fBMpY0xk5xoa1PuRM");
        Count_2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists() && documentSnapshot != null) {
                        countres = documentSnapshot.getLong("count").intValue();
                        System.out.println("blah blah  " + countres);
                        Toast.makeText(LauncherActivity.this, "SuccessFully Done!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        DocumentReference Count_3 = db.collection("Review").document("n8wAc0VVO1pQd1tIZXum");
        Count_3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists() && documentSnapshot != null) {
                        countrev = documentSnapshot.getLong("count").intValue();
                        System.out.println("blah blah  " + countrev);
                        Toast.makeText(LauncherActivity.this, "SuccessFully Done!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        disaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this, sqlitestorage.class);
                String s1 = Integer.toString(countdis);
                intent.putExtra("count", s1);
                startActivity(intent);
            }
        });


        response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this, healthSystemMenu.class);
                String s1 = Integer.toString(countres);
                intent.putExtra("count", s1);
                startActivity(intent);
            }
        });

        mapplot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this, MapActivity.class);
                //s1 = Integer.toString(count);
                //intent.putExtra("count",s1);
                intent.putExtra("count", countdis);
                //intent.putExtra("type",text);
                startActivity(intent);
            }
        });

        viewreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LauncherActivity.this, feedback.class);
                String s1 = Integer.toString(countrev);
                intent.putExtra("count", s1);
                startActivity(intent);
            }
        });


       /* sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS("7020458461","Help man");
            }
        });

    }

    private void sendSMS(String phone, String text) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone,null,text,null,null);
    }*/
    }
}


