package com.rgp.breathe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rgp.breathe.model.PeakFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdansari on 3/30/2016.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "app_breathe";

    private static final String TABLE_PEAKFLOW = "peakflow";

    // peak flow Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PEAKFLOWREADING = "peakflow_reading";
    private static final String KEY_DATETIME = "date_time";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_SYMPTOMS = "symptoms";
    private static final String KEY_TRIGGERS = "triggers";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PEAKFLOW_TABLE = "CREATE TABLE " + TABLE_PEAKFLOW + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_PEAKFLOWREADING + " TEXT," + KEY_DATETIME
                + " DATETIME DEFAULT CURRENT_TIMESTAMP," + KEY_LOCATION + " TEXT," + KEY_SYMPTOMS
                + " TEXT," + KEY_TRIGGERS + " TEXT" + ")";
        db.execSQL(CREATE_PEAKFLOW_TABLE);

        Log.d(TAG, "Database peakflow table created");
    }

    public void addPeakFlowEntry(PeakFlow peakFlow) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PEAKFLOWREADING, peakFlow.getPeakFlowReading());
        values.put(KEY_LOCATION, peakFlow.getGeoLocation());
        //values.put(KEY_SYMPTOMS, peakFlow.getSymptomsList());
        //values.put(KEY_TRIGGERS, peakFlow.getTriggersList());

        // Inserting Row
        long id = db.insert(TABLE_PEAKFLOW, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New peak flow entry inserted into sqlite with id : " + id);
    }

    public List<PeakFlow> getPeakFlowList() {
        List<PeakFlow> peakFlowList = new ArrayList<>();

        String selectQuery =
                "SELECT  peakflow_reading,  datetime(date_time, 'localtime') as Time, location FROM "
                        + TABLE_PEAKFLOW + " ORDER BY " + KEY_DATETIME + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            peakFlowList.add(
                    new PeakFlow(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching peakFlow list from Sqlite: " + peakFlowList);
        return peakFlowList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void cleanData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows from peakflow table
        db.delete(TABLE_PEAKFLOW, null, null);
        db.close();
        Log.d(TAG, "Deleted all peakflow entry from sqlite");
    }
}
