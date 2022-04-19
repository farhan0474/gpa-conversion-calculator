package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "first_db", null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE course (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS course";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public long addValue(String titleInput, String descriptionInput) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", titleInput);
        contentValues.put("description", descriptionInput);
        return sqLiteDatabase.insert("course", null, contentValues);
    }

    public Cursor displayValue() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course", null);
        return cursor;
    }

    public Cursor deleteValue(String titleInput) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course WHERE title = ?", new String[]{titleInput});

        if(cursor.getCount() > 0) {
            sqLiteDatabase.delete("course", "title = ?", new String[]{titleInput});
        }
        return cursor;
    }

    public Cursor editValue(String titleInput, String descriptionInput) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course WHERE title = ?", new String[]{titleInput});

        if(cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", titleInput);
            contentValues.put("description", descriptionInput);
            sqLiteDatabase.update("course", contentValues, "title = ?", new String[]{titleInput});
        }
        return cursor;
    }
}
