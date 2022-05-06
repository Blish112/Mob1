package com.egor.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "taskDb";
    public static final String TABLE_TASKS = "tasks";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DONE = "done";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_TASKS + "(" + KEY_ID
                + " integer primary key," + KEY_TITLE + " text," + KEY_DESCRIPTION + " text," + KEY_DONE + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_TASKS);

        onCreate(sqLiteDatabase);
    }
}
