package com.example.e_cal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
    /* 01 Variables */
    private static final String databaseName = "ecalapp";
    private static final int databaseVersion = 1;

    /* 02 Database Variables */
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    /* 03 Class DbAdapter */
    public DBAdapter (Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /* 04 DatabaseHelper */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //create tables
                db.execSQL("CREATE TABLE IF NOT EXISTS food " +
                        "(food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "food_name VARCHAR, " +
                        "food_manufactor_name VARCHAR);");
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS food");
            onCreate(db);

            String TAG = "tag";
            Log.w(TAG, "Upgrading database from version" + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        }
    }

    /* 05 Open Database */
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    /* 06 Close Database */
    public void close() {
        DBHelper.close();
    }
}
