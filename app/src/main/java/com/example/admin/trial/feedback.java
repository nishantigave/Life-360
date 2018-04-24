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

public class feedback extends AppCompatActivity {
    Button view1,fetch;
    SQLiteDatabase SQL;
    String a1,a2,x;
    FirebaseFirestore db;
    int count,pch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        view1 = (Button) findViewById(R.id.view);
        fetch = (Button) findViewById(R.id.fetch);
        db = FirebaseFirestore.getInstance();
        x= getIntent().getStringExtra("count");
        count = Integer.parseInt(x);
        System.out.println("Value of X : "+x);

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQL = openOrCreateDatabase("Firestore", 0, null);
                SQL.execSQL("drop table if exists Review;");
                SQL.execSQL("create table Review(username varchar(40),review varchar(300));");
                for (int i = 1; i <= count; i++) {
                    DocumentReference feedback = db.collection("Review").document(Integer.toString(i));
                    feedback.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot Doc = task.getResult();
                            if (Doc.exists() && Doc != null) {
                                a1 = Doc.getString("username");
                                a2 = Doc.getString("feedback");


                                System.out.println("lololol" + a1 + "\t" + a2 + "\t");
                                SQL.execSQL("insert into Review values(\"" + a1 + "\",\"" + a2 + "\");");
                                Toast.makeText(feedback.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                                pch = 1;
                                //i++;
                                view1.setVisibility(View.VISIBLE);

                            }
                        }
                    });
                }
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(feedback.this, viewReview.class);
                String s1 = Integer.toString(count);
                intent.putExtra("count", s1);
                if (pch == 1) {
                    startActivity(intent);


                }
            }
        });

    }
}
