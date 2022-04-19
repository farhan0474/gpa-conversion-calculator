package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText title, des;
    Button add, edit, delete, display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.titleVal);
        des = findViewById(R.id.desVal);
        add = findViewById(R.id.add);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        display = findViewById(R.id.display);

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.getReadableDatabase();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleInput = title.getText().toString();
                String descriptionInput = des.getText().toString();

                if(titleInput.equals("") || descriptionInput.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a value!", Toast.LENGTH_SHORT).show();
                } else {
                    long l = dbHelper.addValue(titleInput, descriptionInput);

                    if(l < 0) {
                        Toast.makeText(getApplicationContext(), "Can't add the value!", Toast.LENGTH_SHORT).show();
                    } else {
                        title.setText("");
                        des.setText("");
                        Toast.makeText(getApplicationContext(), "Value has been added!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleInput = title.getText().toString();
                Cursor cursor = dbHelper.deleteValue(titleInput);

                if(cursor.getCount() > 0) {
                    Toast.makeText(getApplicationContext(), "Value has been deleted!", Toast.LENGTH_SHORT).show();
                    title.setText("");
                    des.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleInput = title.getText().toString();
                String descriptionInput = des.getText().toString();
                Cursor cursor = dbHelper.editValue(titleInput, descriptionInput);

                if(cursor.getCount() > 0) {
                    Toast.makeText(getApplicationContext(), "Title/Description has been updated!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = dbHelper.displayValue();

                if (cursor.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No data found!", Toast.LENGTH_SHORT).show();
                } else {
                    String storeData = "";
                    while (cursor.moveToNext()) {
                        storeData += "\n Title:" + cursor.getString(1) + ", Description: " + cursor.getString(2);
                    }
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setCancelable(true);
                    alertDialog.setTitle("Course Table Data");
                    alertDialog.setMessage(storeData);
                    alertDialog.show();
                }
            }
        });
    }
}