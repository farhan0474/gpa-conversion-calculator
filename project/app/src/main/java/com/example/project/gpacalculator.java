package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gpacalculator extends AppCompatActivity {

    Button addgrades, deletegrades, updategrades, viewgpa, back;

    //Used for referring to the database
    DBHelper dbHelper;

    //Used for referring to the instance of this activity
    private static gpacalculator instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);

        //Setting the instance variable to this activity
        instance = this;

        //Creating a database
        dbHelper = new DBHelper(this);
        dbHelper.getReadableDatabase();

        addgrades = findViewById(R.id.button_addgrades);
        deletegrades = findViewById(R.id.button_deletegrades);
        updategrades = findViewById(R.id.button_updategrades);
        viewgpa = findViewById(R.id.button_viewgpa);
        back = findViewById(R.id.button5);

        addgrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gpacalculator.this, addgrades.class));
            }
        });

        deletegrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gpacalculator.this, deletegrades.class));
            }
        });

        updategrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gpacalculator.this, updategrades.class));
            }
        });

        viewgpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gpacalculator.this, viewgpa.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(gpacalculator.this, menu.class));
            }
        });
    }

    /*
    Method used for getting the instance of this activity
     */
    public static gpacalculator getInstance() {
        return instance;
    }

    /*
    Method used for getting the database used in this activity
     */
    public DBHelper getDatabase(){

        return dbHelper;
    }
}