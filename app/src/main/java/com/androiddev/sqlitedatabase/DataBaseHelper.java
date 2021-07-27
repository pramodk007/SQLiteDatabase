package com.androiddev.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static  final String DATABASE_NAME = "student.db";
    public  static  final String TABLE_NAME = "student_table";
    public  static  final String COL_1 = "ID";
    public  static  final String COL_2 = "NAME";
    public  static  final String COL_3 = "SURNAME";
    public  static  final String COL_4 = "MARKS";

    //constructor
    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME ,null, 1);
    }
    //method 1: FOR CREATING THE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                                                "NAME TEXT ," +
                                                "SURNAME TEXT ," +
                                                "MARKS TEXT)");
    }
    //method 2: FOR DELETING THE TABLE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    //method 3: insert the values in the table
    public boolean insertData(String name,String surname,String marks) {

        SQLiteDatabase db = this.getWritableDatabase();

        //values we are inserting in the table
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);

        //we will insert the values using (db) object
        long result = db.insert(TABLE_NAME, null, contentValues);

        //if it is unsuccessful it returns -1
        //checking whether data is insert or not
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //method 4: to fetch the database table using cursor
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }
    //method 5: to update the existing value
    public boolean updateData(String id,String name,String surname,String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        //values we are inserting in the table
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);
        long dataUpdated =db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if(dataUpdated == -1){
            return false;
        }else{
            return true;
        }
    }
    //method 6: to delete the data from the table
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }





}
