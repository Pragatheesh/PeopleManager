package com.pg.peoplemanager.database;

import android.provider.BaseColumns;

/**
 * FeedReaderContract.java - Wraps all the FeedEntry constants
 *
 * @author PG
 * @version 1.0
 */
public class FeedReaderContract {

    /**
     * To prevent from accidentally instantiating
     * the FeedReaderContract class
     */
    public FeedReaderContract() {
    }

    /**
     * Inner Class that defines the People table contents
     */
    public static abstract class PeopleFeedEntry implements BaseColumns {
        public static final String TABLE_PEOPLE = "people";

        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_AGE = "age";
        public static final String KEY_TYPE = "type";

        public static final String CREATE_PEOPLE_TABLE = "CREATE TABLE " + TABLE_PEOPLE
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_AGE + " INTEGER,"
                + KEY_TYPE + " INTEGER"
                + ")";

        public static final String DELETE_PEOPLE_TABLE = "DROP TABLE IF EXISTS " + TABLE_PEOPLE;
    }

    /**
     * Inner Class that defines the Teacher table contents
     */
    public static abstract class TeacherFeedEntry implements BaseColumns {
        public static final String TABLE_TEACHER = "teacher";

        public static final String KEY_ID = "_id";
        public static final String KEY_PEOPLE_ID = "people_id";
        public static final String KEY_TEACHING_GRADE = "teaching_grade";

        public static final String CREATE_TEACHER_TABLE = "CREATE TABLE " + TABLE_TEACHER
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PEOPLE_ID + " INTEGER,"
                + KEY_TEACHING_GRADE + " TEXT"
                + ")";

        public static final String DELETE_TEACHER_TABLE = "DROP TABLE IF EXISTS " + TABLE_TEACHER;
    }

    /**
     * Inner Class that defines the Student table contents
     */
    public static abstract class StudentFeedEntry implements BaseColumns {
        public static final String TABLE_STUDENT = "student";

        public static final String KEY_ID = "_id";
        public static final String KEY_PEOPLE_ID = "people_id";
        public static final String KEY_FAVOURITE_SUBJECT = "favourite_subject";

        public static final String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PEOPLE_ID + " INTEGER,"
                + KEY_FAVOURITE_SUBJECT + " TEXT"
                + ")";

        public static final String DELETE_STUDENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_STUDENT;
    }
}
