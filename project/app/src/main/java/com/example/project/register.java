package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    Button cont;
    Button back;
    Switch switch_remember;
    SharedPreferences sharedPreferences;
    String first, last;
    Boolean isRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        switch_remember = findViewById(R.id.switch_remember);
        cont = findViewById(R.id.button_continue);
        back = findViewById(R.id.back);

        getValues();

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, menu.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveValues();
    }

    protected  void saveValues(){

        sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE);
        first = firstName.getText().toString();
        last = lastName.getText().toString();
        isRemember = switch_remember.isChecked();
        if(isRemember){

            SharedPreferences.Editor sp_editor = sharedPreferences.edit();
            sp_editor.putString("first", first);
            sp_editor.putString("last", last);
            sp_editor.putBoolean("rem", isRemember);
            sp_editor.commit();
            Toast.makeText(getApplicationContext(), "Name saved!", Toast.LENGTH_LONG).show();
        }
    }

    protected void getValues(){
        sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE);

        first = sharedPreferences.getString("first", null);
        last = sharedPreferences.getString("last", null);
        isRemember = sharedPreferences.getBoolean("rem", false);

        firstName.setText(first);
        lastName.setText(last);
        switch_remember.setChecked(isRemember);
    }
}