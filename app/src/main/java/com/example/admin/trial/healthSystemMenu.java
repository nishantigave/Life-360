package com.example.admin.trial;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class healthSystemMenu extends AppCompatActivity {

    FirebaseFirestore db;
    SQLiteDatabase SQL;
    Button adddata,viewdata,fetch;
    int count,counter;
    String s1,a1,a2,a3,cnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_system_menu);
        adddata = (Button) findViewById(R.id.add);
        viewdata = (Button) findViewById(R.id.view);
        fetch = (Button) findViewById(R.id.fetch);
        /*Intent myintent = this.getIntent();
        cnt = myintent.getStringExtra("counter");
        System.out.println("String value : "+cnt);
        counter = Integer.parseInt(cnt);*/
        db = FirebaseFirestore.getInstance();

        DocumentReference count_1 = db.collection("Response").document("zD5PDSPzyDumLwYAdB0M");
        count_1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists() && documentSnapshot != null) {
                        count = documentSnapshot.getLong("count").intValue();
                        System.out.println("blah blahdad  " + count);
                        Toast.makeText(healthSystemMenu.this, "SuccessFully Done!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQL = openOrCreateDatabase("Firestore", 0, null);
                SQL.execSQL("drop table if exists Response;");
                SQL.execSQL("create table Response(name varchar(40),response varchar(300),responsers varchar(100));");
                for (int i = 1; i <= count; i++) {
                    DocumentReference Location = db.collection("Response").document(Integer.toString(i));
                    Location.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot Doc = task.getResult();
                            if (Doc.exists() && Doc != null) {
                                a1 = Doc.getString("Disaster name");
                                a2 = Doc.getString("response");
                                a3 = Doc.getString("responsers");

                                System.out.println("lololol" + a1 +"\t"+ a2 +"\t"+ a3 +"\t");
                                SQL.execSQL("insert into Response values(\"" + a1 + "\",\"" + a2 + "\",\"" + a3 + "\");");
                                Toast.makeText(healthSystemMenu.this,"Successfully inserted",Toast.LENGTH_SHORT).show();
                                //i++;

                            }
                        }
                    });
                }
                viewdata.setVisibility(View.VISIBLE);
                adddata.setVisibility(View.VISIBLE);

            }
        });
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(healthSystemMenu.this,healthDataAdd.class);
                if(count!=0) {
                    s1 = Integer.toString(count);
                    intent.putExtra("count", s1);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(healthSystemMenu.this,"Count cant be zero",Toast.LENGTH_SHORT).show();
                }
            }
        });


        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(healthSystemMenu.this,viewResponse.class);
                if(count!=0){
                    s1 = Integer.toString(count);
                    intent.putExtra("count",s1);
                    startActivity(intent);
                }
            }
        });
    }
}
