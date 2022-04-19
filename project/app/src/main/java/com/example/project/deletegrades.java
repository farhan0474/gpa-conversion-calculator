package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deletegrades extends AppCompatActivity {

    EditText name;
    Button delete, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletegrades);

        name = findViewById(R.id.name3);

        delete = findViewById(R.id.delete);
        back = findViewById(R.id.button8);

        DBHelper dbHelper = gpacalculator.getInstance().getDatabase();
        dbHelper.getReadableDatabase();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(deletegrades.this, gpacalculator.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameInput = name.getText().toString();
                if(nameInput.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter the course name!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor cursor = dbHelper.deleteValue(nameInput);

                    if(cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Course has been deleted!", Toast.LENGTH_SHORT).show();
                        name.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "No data found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}