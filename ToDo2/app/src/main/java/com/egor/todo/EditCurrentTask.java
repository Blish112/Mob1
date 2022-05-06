package com.egor.todo;

import static com.egor.todo.DBHelper.KEY_DESCRIPTION;
import static com.egor.todo.DBHelper.KEY_TITLE;
import static com.egor.todo.DBHelper.TABLE_TASKS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditCurrentTask extends AppCompatActivity {

    String[] selectionArgs;
    DBHelper dbHelper;
    EditText edTitle;
    EditText edDescription;
    private int taskId;
    public static final String EXTRA_TASK_ID = "taskId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_current_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edTitle = findViewById(R.id.editTitle);
        edDescription = findViewById(R.id.editDescription);
        taskId = (Integer) getIntent().getExtras().get(EXTRA_TASK_ID);
        selectionArgs = new String[] {String.valueOf(taskId)};
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(TABLE_TASKS, null, "_id = ?", selectionArgs, null, null, null);
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int titleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
            int descriptionIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION);
            int doneIndex = cursor.getColumnIndex(DBHelper.KEY_DONE);
            cursor.getInt(idIndex);
            cursor.getInt(doneIndex);
            edTitle.setText(cursor.getString(titleIndex));
            edDescription.setText(cursor.getString(descriptionIndex));
        }
        cursor.close();
        dbHelper.close();

    }

    public void save(View view) {
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
        if(title.equals("") || description.equals("")){
            Toast.makeText(getApplicationContext(),
                    "заполните поля",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            contentValues.put(KEY_TITLE, title);
            contentValues.put(KEY_DESCRIPTION, description);
            database.update(TABLE_TASKS, contentValues, "_id = ?", selectionArgs);
            dbHelper.close();

            Toast.makeText(getApplicationContext(),
                    "сохранено",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(TABLE_TASKS, "_id = " + taskId, null);
        dbHelper.close();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}