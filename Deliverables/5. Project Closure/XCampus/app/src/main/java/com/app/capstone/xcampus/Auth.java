package com.app.capstone.xcampus;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.capstone.xcampus.dataLayer.Services;

public class Auth extends AppCompatActivity {

    private Context context;
    private Button getEntries, getfavs, getNotifs , getRatings;
    private TextView entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        context = this;





        /*
        getEntries = (Button) findViewById(R.id.getEntries);
        getfavs = (Button) findViewById(R.id.getFavs);
        getNotifs = (Button) findViewById(R.id.getNotifications);
        getRatings = (Button) findViewById(R.id.getRatings);


        entries = (TextView) findViewById(R.id.entries);

        getEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Services.getEntries(context,  entries);
            }
        });

        getfavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Services.getFavs(context , entries);
            }
        });

        getNotifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Services.getNotifs(context, entries);
            }
        });

        getRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Services.getRatings(context, entries);
            }
        });
        */


    }




}
