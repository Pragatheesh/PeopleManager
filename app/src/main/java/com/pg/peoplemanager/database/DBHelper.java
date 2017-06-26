package com.pg.peoplemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * DBHelper.java The database helper class
 * which does all the interactions to the database
 *
 * @author PG
 * @version 1.0
 * @see SQLiteOpenHelper
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * The current version of the database
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * The name of the database stored in the FS
     */
    private static final String DATABASE_NAME = "peopleManager.db";

    /**
     * The single instance of DBHelper for the whole application
     */
    private static DBHelper sInstance;

    /**
     * Private constructor to initialize the super class
     * along with the application context
     *
     * @param applicationContext the app application context
     */
    private DBHelper(Context applicationContext) {
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method to get the DBHelper instance to make
     * further interactions to the database
     *
     * @param context the context of the application
     * @return the single instance of the DBHelper class
     */
    public static synchronized DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper OnCreate", "onCreate");
        db.execSQL(FeedReaderContract.PeopleFeedEntry.CREATE_PEOPLE_TABLE);
        db.execSQL(FeedReaderContract.TeacherFeedEntry.CREATE_TEACHER_TABLE);
        db.execSQL(FeedReaderContract.StudentFeedEntry.CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
