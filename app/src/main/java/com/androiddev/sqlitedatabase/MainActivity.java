package com.androiddev.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //reference for the databaseHelper class
    DataBaseHelper myDb;

    EditText editText_id;
    EditText editText_name;
    EditText editText_Lastname;
    EditText editText_marks;
    
    Button button_insert;
    Button button_update;
    Button button_view;
    Button button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_id = findViewById(R.id.editTextID);
        editText_name = findViewById(R.id.editTextName);
        editText_Lastname = findViewById(R.id.editTextLastname);
        editText_marks = findViewById(R.id.editTextMarks);

        button_insert = findViewById(R.id.button_insert);
        button_update = findViewById(R.id.button_update);
        button_view = findViewById(R.id.button_view);
        button_delete = findViewById(R.id.button_delete);

        //object for the DatabaseHelper class
        myDb = new DataBaseHelper(this);
        myDb.getWritableDatabase();

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dataInserted = myDb.insertData(editText_name.getText().toString(),editText_Lastname.getText().toString(),editText_marks.getText().toString());
                if(dataInserted){
                    Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "insertion process failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dataUpdated = myDb.updateData(editText_id.getText().toString(),editText_name.getText().toString(),editText_Lastname.getText().toString(),editText_marks.getText().toString());
                if(dataUpdated){
                    Toast.makeText(getApplicationContext(), "data is updated successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "updating process failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDb.getAllData();
                //need loop for fetching hte data from each row
                if(cursor.getCount() == 0){
                    dialogBuilder("DATA TABLE","TABLE IS EMPTY!! NO DATA TO SHOW");
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("ID :"+cursor.getString(0)+"\n");
                    buffer.append("NAME :"+cursor.getString(1)+"\n");
                    buffer.append("LASTNAME :"+cursor.getString(2)+"\n");
                    buffer.append("MARKS :"+cursor.getString(3)+"\n");
                }
                dialogBuilder("DATA TABLE",buffer.toString());
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRow = myDb.deleteData(editText_id.getText().toString());
                if(deletedRow > 0) {
                    Toast.makeText(getApplicationContext(), "data deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "no data to deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public  void dialogBuilder(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}