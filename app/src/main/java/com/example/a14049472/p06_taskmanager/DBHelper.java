package com.example.a14049472.p06_taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskmanager.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TASK_NAME = "task_name";
    private static final String COLUMN_TASK_DESCRIPTION = "task_description";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTaskTableSql="CREATE TABLE " + TABLE_TASK + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK_NAME + " TEXT , " + COLUMN_TASK_DESCRIPTION + " TEXT ) ";
        db.execSQL(createTaskTableSql);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);

    }
    public long insertTask (String taskName,String taskDescription){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME,taskName);
        values.put(COLUMN_TASK_DESCRIPTION,taskDescription);
        long result = db.insert(TABLE_TASK,null,values);
        db.close();
        return result;
    }

     public ArrayList<String> getAllTask() {
         ArrayList<String> notes = new ArrayList<String>();

         String selectQuery = "SELECT " + COLUMN_ID + ","
                 + COLUMN_TASK_NAME + ","+ COLUMN_TASK_DESCRIPTION + " FROM " + TABLE_TASK;

         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);
         if (cursor.moveToFirst()) {
             do {
                 int id = cursor.getInt(0);
                 String name = cursor.getString(1);
                 String description = cursor.getString(2);
                 notes.add("ID:" + id + ", " + name + ", " + description);
             } while (cursor.moveToNext());
         }
         cursor.close();
         db.close();
         return notes;
     }


 }
