package com.egor.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLData;

public class AddNewTask extends AppCompatActivity {

    EditText inputTitle, inputDescription;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputTitle = findViewById(R.id.inputTitle);
        inputDescription = findViewById(R.id.inputDescription);
        dbHelper = new DBHelper(this);
    }

    public void createTask(View view) {
        String title = inputTitle.getText().toString();
        String description = inputDescription.getText().toString();
        if(title.equals("") || description.equals("")){
            Toast.makeText(getApplicationContext(),
                    "заполните поля",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(DBHelper.KEY_TITLE, title);
            contentValues.put(DBHelper.KEY_DESCRIPTION, description);
            contentValues.put(DBHelper.KEY_DONE, 0);

            database.insert(DBHelper.TABLE_TASKS, null, contentValues);

            dbHelper.close();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}