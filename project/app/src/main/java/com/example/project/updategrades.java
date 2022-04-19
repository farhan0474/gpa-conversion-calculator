package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updategrades extends AppCompatActivity {

    EditText semester, name, grade;
    Button update, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updategrades);

        semester = findViewById(R.id.semester2);
        name = findViewById(R.id.name2);
        grade = findViewById(R.id.grade2);

        update = findViewById(R.id.update);
        back = findViewById(R.id.button9);

        DBHelper dbHelper = gpacalculator.getInstance().getDatabase();
        dbHelper.getReadableDatabase();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(updategrades.this, gpacalculator.class));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int semesterId = 0, gradeInput = 0;
                semesterId = Integer.parseInt(semester.getText().toString());
                String nameInput = name.getText().toString();
                gradeInput = Integer.parseInt(grade.getText().toString());

                if(nameInput.equals("") || semesterId == 0 || gradeInput == 0) {
                    Toast.makeText(getApplicationContext(), "Enter a value!", Toast.LENGTH_SHORT).show();
                }

                else {
                    Cursor cursor = dbHelper.editValue(semesterId, nameInput, gradeInput);
                    if(cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Grade has been updated!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No data found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}