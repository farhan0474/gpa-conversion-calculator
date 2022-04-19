package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class viewgpa extends AppCompatActivity {

    TextView title, transcript;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgpa);


        back = findViewById(R.id.button10);

        title = findViewById(R.id.titleInput);
        transcript = findViewById(R.id.transcritpt);
        transcript.setMovementMethod(new ScrollingMovementMethod());
        DBHelper dbHelper = gpacalculator.getInstance().getDatabase();
        dbHelper.getReadableDatabase();

        Cursor cursor = dbHelper.getSemesterIDs();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data found!", Toast.LENGTH_SHORT).show();
        }

        else{

            title.setText("Transcript");
            StringBuilder output = new StringBuilder("\n\n");
            int[] semesterNumbers = new int[8];
            int[] gradeNumbers = new int[40];
            int totalSemesters = 0;
            int totalCourses = 0;
            int totalGrades = 0;
            while(cursor.moveToNext()){

                semesterNumbers[totalSemesters] = cursor.getInt(0);
                totalSemesters++;
            }

            for(int i = 0;i < totalSemesters; i++){

                output.append("Semester ").append(semesterNumbers[i]).append(":\n\n");
                output.append("Course Name  ").append("Grade\n\n");
                Cursor cursor1 = dbHelper.displayValue(semesterNumbers[i]);
                while(cursor1.moveToNext()){

                    output.append(cursor1.getString(1)).append("  ").append(cursor1.getInt(2)).append("\n\n");
                    totalCourses++;
                    totalGrades+=cursor1.getInt(2);
                }
                output.append("\n\n");
            }
            output.append("Total courses: ").append(totalCourses);
            float GPA = ((float) totalGrades) / totalCourses;
            String formatted = String.format("%.2f", GPA);
            output.append("\n\nOverall GPA:"  ).append(formatted);
            transcript.setText(output);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewgpa.this, gpacalculator.class));
            }
        });

    }
}