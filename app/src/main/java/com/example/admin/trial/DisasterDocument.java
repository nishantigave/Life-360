package com.example.admin.trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class DisasterDocument extends AppCompatActivity {

    ImageButton disaster,response;
    int count;
    String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disaster_document);
        disaster = (ImageButton)findViewById(R.id.disasterstats);
        response = (ImageButton) findViewById(R.id.healthsystemresponse);
       /* s1 = getIntent().getStringExtra("count");
        count = Integer.parseInt(s1);*/

       /* disaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisasterDocument.this, sqlitestorage.class);
                if (count != 0) {
                    s1 = Integer.toString(count);
                    //s2 = Double.toString(longl);
                    intent.putExtra("count", s1);
                    //intent.putExtra("Longitude", s2);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(DisasterDocument.this,"count cant be zero",Toast.LENGTH_SHORT).show();
                }
            }
        });

        response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisasterDocument.this,healthSystemMenu.class);
                startActivity(intent);
            }
        });*/
    }
}
