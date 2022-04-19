package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "project", null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE course (SemesterId INTEGER, name TEXT PRIMARY KEY, Grade INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS course";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public long addValue(int semesterId, String nameInput, int grade) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SemesterId", semesterId);
        contentValues.put("name", nameInput);
        contentValues.put("Grade", grade);
        return sqLiteDatabase.insert("course", null, contentValues);
    }

    public Cursor displayValue(int semesterId) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course WHERE SemesterId= ?", new String[]{String.valueOf(semesterId)});
        return cursor;
    }

    public Cursor deleteValue(String nameInput) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course WHERE name = ?", new String[]{nameInput});

        if(cursor.getCount() > 0) {
            sqLiteDatabase.delete("course", "name = ?", new String[]{nameInput});
        }
        return cursor;
    }

    public Cursor editValue(int semesterId, String nameInput, int grade) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course WHERE name = ?", new String[]{nameInput});

        if(cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("SemesterId", semesterId);
            contentValues.put("name", nameInput);
            contentValues.put("Grade", grade);
            sqLiteDatabase.update("course", contentValues, "name = ?", new String[]{nameInput});
        }
        return cursor;
    }

    public Cursor getSemesterIDs(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT SemesterId FROM course ORDER BY SemesterId ASC", null);
        return cursor;
    }
}