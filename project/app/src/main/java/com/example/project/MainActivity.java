package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView des1;
    TextView des2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        des1 = findViewById(R.id.des1);
        des2 = findViewById(R.id.des2);
        btn = findViewById(R.id.btn);

        des1.setText("** The GPA calculator is used to calculate grade point average (GPA) and generate a GPA report.\n\n** Enter semester number, course code, name & mark.\n\n** Get GPA for each semester.");
        des2.setText("** The Conversion Calculator is used to convert between commonly used units.\n\n** Select the type of converter and add the amount.\n\n** Get the converted value.");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This is used for starting the second activity
                Intent i = new Intent(MainActivity.this, register.class);
                startActivity(i);
            }
        });

    }
}