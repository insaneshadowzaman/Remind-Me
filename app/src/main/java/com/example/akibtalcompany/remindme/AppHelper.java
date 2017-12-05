package com.example.akibtalcompany.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class AppHelper {

    public static final String TABLE_NAME = "NOTES", COL_NAME = "name", COL_HH = "hh", COL_MM = "mm";

    public static final Integer NOTIFICATION_ID = 765;

    public static SQLiteDatabase getDatabase(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase("NOTES", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NOTES(name TEXT, hh INTEGER, mm TEXT)");
        return db;
    }

    public static void addNoteToDB(Context context, String name, Integer hh, Integer mm) {
        SQLiteDatabase db = context.openOrCreateDatabase("NOTES", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NOTES(name, ampm, hh, mm)");
//        db.execSQL("INSERT INTO NOTES VALUE('" + name + "' , '" + ampm + "', " + String.valueOf(hh) + ", " + String.valueOf(mm) + ");");
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_HH, hh);
        cv.put(COL_MM, mm);
        db.insert(TABLE_NAME, null, cv);

    }

    public static void dumbDB(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase("NOTES", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NOTES(name, ampm, hh, mm)");
        Cursor cursor = db.query("NOTES", new String[] {"name", "ampm", "hh", "mm"}, null, null, null, null, null);
        DatabaseUtils.dumpCursor(cursor);
        cursor.close();
    }

    public static Cursor getCursor(Context context) {
        SQLiteDatabase db = context.openOrCreateDatabase("NOTES", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NOTES(name, ampm, hh, mm)");
        Cursor cursor = db.query("NOTES", new String[] {"name", "ampm", "hh", "mm"}, null, null, null, null, null);
        return cursor;
    }
    public static void deleteNote(Context context, String name) {
        SQLiteDatabase db = context.openOrCreateDatabase("NOTES", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NOTES(name, ampm, hh, mm)");
        db.delete(TABLE_NAME, COL_NAME + "=?", new String[]{name});
    }
}
