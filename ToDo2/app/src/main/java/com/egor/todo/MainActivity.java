package com.egor.todo;

import static com.egor.todo.DBHelper.KEY_DONE;
import static com.egor.todo.DBHelper.TABLE_TASKS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    public String[] titles;
    public String[] descriptions;
    public int[] _id;
    public int[] done;
    public long count;
    public RecyclerView taskRecycler;
    TextView tv;
    Boolean filter;
    TextView textfiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv = findViewById(R.id.null_tasks);
        taskRecycler = (RecyclerView) findViewById(R.id.tasks_recycler);
        textfiler = findViewById(R.id.textfilter);
        filter = true;
        textfiler.setText("текущие задачи");

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        count = DatabaseUtils.queryNumEntries(database, TABLE_TASKS);
        String[]titles1 = new String[(int) count];
        String[]descriptions1 = new String[(int) count];
        int[] _id1 = new int[(int) count];
        int[] done1 = new int[(int) count];

        Cursor cursor = database.query(TABLE_TASKS, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            tv.setVisibility(View.GONE);
            int i = 0;
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int titleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
            int descriptionIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION);
            int doneIndex = cursor.getColumnIndex(DBHelper.KEY_DONE);
            do {
                if(filter == true){
                    if(cursor.getInt(doneIndex) == 0){
                        done1[i] = cursor.getInt(doneIndex);
                        _id1[i] = cursor.getInt(idIndex);
                        titles1[i] = cursor.getString(titleIndex);
                        descriptions1[i] = cursor.getString(descriptionIndex);
                        ++i;
                    }
                }
                else {
                    if(cursor.getInt(doneIndex) == 1){
                        done1[i] = cursor.getInt(doneIndex);
                        _id1[i] = cursor.getInt(idIndex);
                        titles1[i] = cursor.getString(titleIndex);
                        descriptions1[i] = cursor.getString(descriptionIndex);
                        ++i;
                    }
                }
            }while (cursor.moveToNext());
        }
        else{
            tv.setVisibility(View.VISIBLE);
        }
        int cnt = 0;
        for (int i = 0; i < titles1.length; i++){
            if(titles1[i] != null) {
                cnt++;
            }
        }

        titles = new String[cnt];
        descriptions = new String[cnt];
        _id = new int[cnt];
        done = new int[cnt];
        for(int i = 0; i < cnt; i++){
            titles[i] = titles1[i];
            descriptions[i] = descriptions1[i];
            _id[i] = _id1[i];
            done[i] = done1[i];
        }
        cursor.close();
        dbHelper.close();
        TaskAdapter adapter = new TaskAdapter(titles, descriptions, _id, done);
        taskRecycler.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        taskRecycler.setLayoutManager(linearLayoutManager);
        if(adapter.getItemCount() == 0){
            tv.setVisibility(View.VISIBLE);
        }
    }

    public void fab(View view) {
        Intent intent = new Intent(this, AddNewTask.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.current:
                Toast.makeText(getApplicationContext(),
                        "текущие задачи",
                        Toast.LENGTH_SHORT).show();
                filter = true;
                textfiler.setText("текущие задачи");
                filter_activated();
                break;
            case R.id.done:
                Toast.makeText(getApplicationContext(),
                        "выполненые задачи",
                        Toast.LENGTH_SHORT).show();
                filter = false;
                textfiler.setText("выполненые задачи");
                filter_activated();
                break;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        count = DatabaseUtils.queryNumEntries(database, TABLE_TASKS);
        String[] titles1 = new String[(int) count];
        String[] descriptions1 = new String[(int) count];
        int[] _id1 = new int[(int) count];
        int[] done1 = new int[(int) count];

        Cursor cursor = database.query(TABLE_TASKS, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            tv.setVisibility(View.GONE);
            int i = 0;
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int titleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
            int descriptionIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION);
            int doneIndex = cursor.getColumnIndex(DBHelper.KEY_DONE);
            do {
                if(filter == true){
                    if(cursor.getInt(doneIndex) == 0){
                        done1[i] = cursor.getInt(doneIndex);
                        _id1[i] = cursor.getInt(idIndex);
                        titles1[i] = cursor.getString(titleIndex);
                        descriptions1[i] = cursor.getString(descriptionIndex);
                        ++i;
                    }
                }
                else {
                    if(cursor.getInt(doneIndex) == 1){
                        done1[i] = cursor.getInt(doneIndex);
                        _id1[i] = cursor.getInt(idIndex);
                        titles1[i] = cursor.getString(titleIndex);
                        descriptions1[i] = cursor.getString(descriptionIndex);
                        ++i;
                    }
                }
            }while (cursor.moveToNext());
        }
        else{
            tv.setVisibility(View.VISIBLE);
        }
        int cnt = 0;
        for (int i = 0; i < titles1.length; i++){
            if(titles1[i] != null) {
                cnt++;
            }
        }

        titles = new String[cnt];
        descriptions = new String[cnt];
        _id = new int[cnt];
        done = new int[cnt];
        for(int i = 0; i < cnt; i++){
            titles[i] = titles1[i];
            descriptions[i] = descriptions1[i];
            _id[i] = _id1[i];
            done[i] = done1[i];
        }
        cursor.close();
        dbHelper.close();
        TaskAdapter adapter = new TaskAdapter(titles, descriptions, _id, done);
        taskRecycler.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        taskRecycler.setLayoutManager(linearLayoutManager);
        if(adapter.getItemCount() == 0){
            tv.setVisibility(View.VISIBLE);
        }
    }
    public void edit_task(View view){
        String value = view.getContentDescription().toString();
        Intent intent = new Intent(this, EditCurrentTask.class);
        intent.putExtra(EditCurrentTask.EXTRA_TASK_ID, Integer.parseInt(value));
        startActivity(intent);
    }
    public void press_checkbox(View view){
        int done = 0;
        String value = view.getContentDescription().toString();
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] selectionArgs = new String[] {value};
        Cursor cursor = database.query(TABLE_TASKS, null, "_id = ?", selectionArgs, null, null, null);
        if(cursor.moveToFirst()){
            int doneIndex = cursor.getColumnIndex(DBHelper.KEY_DONE);
            done = cursor.getInt(doneIndex);
        }

        if(done == 1){
            contentValues.put(KEY_DONE, 0);

        }
        else {
            contentValues.put(KEY_DONE, 1);
        }
        database.update(TABLE_TASKS, contentValues, "_id = ?", selectionArgs);
        dbHelper.close();
        filter_activated();
    }
    public void filter_activated(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        count = DatabaseUtils.queryNumEntries(database, TABLE_TASKS);
        String[] titles1 = new String[(int) count];
        String[] descriptions1 = new String[(int) count];
        int[] _id1 = new int[(int) count];
        int[] done1 = new int[(int) count];
        Cursor cursor = database.query(TABLE_TASKS, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            tv.setVisibility(View.GONE);
            int i = 0;
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int titleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
            int descriptionIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION);
            int doneIndex = cursor.getColumnIndex(DBHelper.KEY_DONE);
            do {
                if(filter == true){
                    if(cursor.getInt(doneIndex) == 0){
                        done1[i] = cursor.getInt(doneIndex);
                        _id1[i] = cursor.getInt(idIndex);
                        titles1[i] = cursor.getString(titleIndex);
                        descriptions1[i] = cursor.getString(descriptionIndex);
                        ++i;
                    }
                }
                else {
                    if(cursor.getInt(doneIndex) == 1){
                        done1[i] = cursor.getInt(doneIndex);
                        _id1[i] = cursor.getInt(idIndex);
                        titles1[i] = cursor.getString(titleIndex);
                        descriptions1[i] = cursor.getString(descriptionIndex);
                        ++i;
                    }
                }
            }while (cursor.moveToNext());
        }
        else{
            tv.setVisibility(View.VISIBLE);
        }
        int cnt = 0;
        for (int i = 0; i < titles1.length; i++){
            if(titles1[i] != null) {
                cnt++;
            }
        }

        titles = new String[cnt];
        descriptions = new String[cnt];
        _id = new int[cnt];
        done = new int[cnt];
        for(int i = 0; i < cnt; i++){
            titles[i] = titles1[i];
            descriptions[i] = descriptions1[i];
            _id[i] = _id1[i];
            done[i] = done1[i];
        }
        cursor.close();
        dbHelper.close();
        TaskAdapter adapter = new TaskAdapter(titles, descriptions, _id, done);
        taskRecycler.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        taskRecycler.setLayoutManager(linearLayoutManager);
        if(adapter.getItemCount() == 0){
            tv.setVisibility(View.VISIBLE);
        }
    }
}