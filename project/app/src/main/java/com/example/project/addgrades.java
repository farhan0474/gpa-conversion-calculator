package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addgrades extends AppCompatActivity {

    EditText semester, name, grade;
    Button add, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgrades);

        semester = findViewById(R.id.semester);
        name = findViewById(R.id.name);
        grade = findViewById(R.id.grade);

        add = findViewById(R.id.add);
        back = findViewById(R.id.back7);

        DBHelper dbHelper = gpacalculator.getInstance().getDatabase();
        dbHelper.getReadableDatabase();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addgrades.this, gpacalculator.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
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
                    long l = dbHelper.addValue(semesterId, nameInput, gradeInput);

                    if(l < 0) {
                        Toast.makeText(getApplicationContext(), "Can't add the value!", Toast.LENGTH_SHORT).show();
                    } else {
                        semester.setText("");
                        name.setText("");
                        grade.setText("");
                        Toast.makeText(getApplicationContext(), "Value has been added!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}