package com.example.admin.trial;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class healthDataAdd extends AppCompatActivity {

    Button submit;
    EditText disastername,responsesofhealth,responsers;
    FirebaseFirestore firebase;
    String s1,s2,s3,cnt,x;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_data_add);
        disastername = (EditText)findViewById(R.id.disatername);
        responsesofhealth = (EditText) findViewById(R.id.responseofhealth);
        responsers = (EditText) findViewById(R.id.response);
        submit = (Button) findViewById(R.id.submit);

        Intent myintent = this.getIntent();
        cnt = myintent.getStringExtra("count");
        System.out.println("String value : "+cnt);
        count = Integer.parseInt(cnt);

        firebase = FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = disastername.getText().toString();
                s2 = responsesofhealth.getText().toString();
                s3 = responsers.getText().toString();
                count++;
                cnt = Integer.toString(count);
                System.out.println("String value of incremented count :"+cnt);
                if(s1!=null && s2!=null && s3!=null) {
                    addData();
                    disastername.setText(null);
                    responsesofhealth.setText(null);
                    responsers.setText(null);
                }

                Intent intent = new Intent(healthDataAdd.this,LauncherActivity.class);
                x = Integer.toString(count);
                intent.putExtra("counter", x);
                startActivity(intent);
            }
        });

    }

    private void addData() {
        Map<String,Object> newData = new HashMap<>();
        newData.put("Disaster name",s1);
        newData.put("response",s2);
        newData.put("responsers", s3);
        firebase.collection("Response").document(cnt).set(newData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(healthDataAdd.this,"Succesfully Added!",Toast.LENGTH_SHORT).show();
                System.out.println("Fucker Fucker Fucker :"+count);
                updatedata(count);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(healthDataAdd.this,"Error :"+e.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updatedata(final int count) {

        DocumentReference cntupdate = firebase.collection("Response").document("zD5PDSPzyDumLwYAdB0M");
        cntupdate.update("count",count).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Incremented Count : "+count);
                Toast.makeText(healthDataAdd.this,"Count Incremented!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
