package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class conversioncalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText input;
    TextView output;
    Button back, convert;
    String selectedConversion = "Meter to Kilometer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversioncalculator);

        input = findViewById(R.id.amount);
        output = findViewById(R.id.result);
        back = findViewById(R.id.button6);
        convert = findViewById(R.id.button_convert);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(conversioncalculator.this, menu.class));
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedConversion.equals("Meter to Kilometer")) {
                    output.setText(""+((Double.parseDouble(input.getText().toString()))/1000));
                } else if (selectedConversion.equals("Kilometer to Meter")) {
                    output.setText(""+((Double.parseDouble(input.getText().toString()))*1000));
                } else if (selectedConversion.equals("Kilogram to Pound")) {
                    output.setText(""+((Double.parseDouble(input.getText().toString()))*2.20462262185));
                } else if (selectedConversion.equals("Pound to Kilogram")) {
                    output.setText(""+((Double.parseDouble(input.getText().toString()))/2.20462262185));
                } else if (selectedConversion.equals("Pound to Ounce")) {
                    output.setText(""+((Double.parseDouble(input.getText().toString()))*16));
                } else if (selectedConversion.equals("Ounce to Pound")) {
                    output.setText(""+((Double.parseDouble(input.getText().toString()))/16));
                } else if (selectedConversion.equals("Fahrenheit to Celsius")) {
                    output.setText(""+(((Double.parseDouble(input.getText().toString()))-32)*(5.0/9.0)));
                } else if (selectedConversion.equals("Celsius to Fahrenheit")) {
                    output.setText(""+(((Double.parseDouble(input.getText().toString()))/(5.0/9.0))+32));
                }

            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner_conversion);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedConversion = adapterView.getItemAtPosition(i).toString();
        String selected = "You have selected " + selectedConversion + "!";
        Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
        output.setText("");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}