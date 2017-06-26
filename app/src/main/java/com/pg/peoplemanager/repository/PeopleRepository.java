package com.pg.peoplemanager.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pg.peoplemanager.database.DBHelper;
import com.pg.peoplemanager.database.FeedReaderContract.PeopleFeedEntry;
import com.pg.peoplemanager.database.FeedReaderContract.StudentFeedEntry;
import com.pg.peoplemanager.database.FeedReaderContract.TeacherFeedEntry;
import com.pg.peoplemanager.model.People;
import com.pg.peoplemanager.model.Student;
import com.pg.peoplemanager.model.Teacher;
import com.pg.peoplemanager.util.PeopleType;

import java.util.ArrayList;

/**
 * PeopleRepository.java - Handle the data access related to the Person
 *
 * @author PG
 * @version 1.0
 * @see DBHelper
 * @see Teacher
 * @see Student
 */

public class PeopleRepository {
    private final DBHelper dbHelper;

    /**
     * Constructor to initialize the dbHelper
     *
     * @param applicationContext the application context from the activity invoking
     */
    public PeopleRepository(Context applicationContext) {
        this.dbHelper = DBHelper.getInstance(applicationContext);
    }

    /**
     * Method to persist a new Teacher object in the database
     *
     * @param teacher an instance of a Teacher which is to be persisted
     * @see Teacher
     */
    public void create(Teacher teacher) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PeopleFeedEntry.KEY_NAME, teacher.getName());
        values.put(PeopleFeedEntry.KEY_AGE, teacher.getAge());
        values.put(PeopleFeedEntry.KEY_TYPE, PeopleType.TEACHER.getId());
        int id = (int) db.insert(PeopleFeedEntry.TABLE_PEOPLE, null, values);

        values = new ContentValues();
        values.put(TeacherFeedEntry.KEY_PEOPLE_ID, id);
        values.put(TeacherFeedEntry.KEY_TEACHING_GRADE, teacher.getTeachingGrade());
        db.insert(TeacherFeedEntry.TABLE_TEACHER, null, values);

        db.close();
    }

    /**
     * Method to persist a new Student object in the database
     *
     * @param student an instance of a Student which is to be persisted
     * @see Student
     */
    public void create(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PeopleFeedEntry.KEY_NAME, student.getName());
        values.put(PeopleFeedEntry.KEY_AGE, student.getAge());
        values.put(PeopleFeedEntry.KEY_TYPE, PeopleType.STUDENT.getId());
        int id = (int) db.insert(PeopleFeedEntry.TABLE_PEOPLE, null, values);

        values = new ContentValues();
        values.put(StudentFeedEntry.KEY_PEOPLE_ID, id);
        values.put(StudentFeedEntry.KEY_FAVOURITE_SUBJECT, student.getFavouriteSubject());
        db.insert(StudentFeedEntry.TABLE_STUDENT, null, values);

        db.close();
    }

    /**
     * Method to update a Teacher object which is in the database
     *
     * @param teacher an instance of a Teacher which contains an id
     * @see Teacher
     */
    public void update(Teacher teacher) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // update stage values into the STAGES_TABLE
        ContentValues values = new ContentValues();
        values.put(PeopleFeedEntry.KEY_NAME, teacher.getName());
        values.put(PeopleFeedEntry.KEY_AGE, teacher.getAge());

        db.update(PeopleFeedEntry.TABLE_PEOPLE, values, PeopleFeedEntry.KEY_ID + " = ?", new String[]{String.valueOf(teacher.getId())});

        values = new ContentValues();
        values.put(TeacherFeedEntry.KEY_TEACHING_GRADE, teacher.getTeachingGrade());
        db.update(TeacherFeedEntry.TABLE_TEACHER, values, TeacherFeedEntry.KEY_PEOPLE_ID + " = ?", new String[]{String.valueOf(teacher.getId())});

        db.close();
    }

    /**
     * Method to update a Student object which is in the database
     *
     * @param student an instance of a Student which contains an id
     * @see Student
     */
    public void update(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // update stage values into the STAGES_TABLE
        ContentValues values = new ContentValues();
        values.put(PeopleFeedEntry.KEY_NAME, student.getName());
        values.put(PeopleFeedEntry.KEY_AGE, student.getAge());

        db.update(PeopleFeedEntry.TABLE_PEOPLE, values, PeopleFeedEntry.KEY_ID + " = ?", new String[]{String.valueOf(student.getId())});

        values = new ContentValues();
        values.put(StudentFeedEntry.KEY_FAVOURITE_SUBJECT, student.getFavouriteSubject());
        db.update(StudentFeedEntry.TABLE_STUDENT, values, StudentFeedEntry.KEY_PEOPLE_ID + " = ?", new String[]{String.valueOf(student.getId())});

        db.close();
    }

    /**
     * Method to delete a single person in the database
     *
     * @param id the id of the Person to be deleted
     */
    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(PeopleFeedEntry.TABLE_PEOPLE, PeopleFeedEntry.KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.delete(TeacherFeedEntry.TABLE_TEACHER, TeacherFeedEntry.KEY_PEOPLE_ID + " = ?", new String[]{String.valueOf(id)});
        db.delete(StudentFeedEntry.TABLE_STUDENT, StudentFeedEntry.KEY_PEOPLE_ID + " = ?", new String[]{String.valueOf(id)});

        db.close();
    }

    /**
     * Get all the by PeopleType in an ordered ArrayList
     * Example - getAllPeople(PeopleType.TEACHER) will return a list of Teachers
     *
     * @param peopleType defines which people type has to be returned in the list
     * @return the list of People filtered by the type requested
     * @see PeopleType
     * @see People
     */
    public ArrayList<People> getAllPeople(PeopleType peopleType) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<People> people = new ArrayList<>();

        //Prepare the main query according to the request made
        String query = getQueryPeopleByType(peopleType);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Get the sub query depending on the PeopleType and the person who attribute is to be retrieved
                Cursor subCursor = db.rawQuery(getQueryPeopleSubTableByType(cursor.getInt(3), cursor.getInt(0)), null);

                if (subCursor.moveToFirst()) {
                    People person = (cursor.getInt(3) == PeopleType.TEACHER.getId())
                            ? new Teacher(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), subCursor.getString(2))
                            : new Student(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), subCursor.getString(2));

                    people.add(person);
                }
                subCursor.close();
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return people;
    }

    /**
     * Method to get query to search people
     * depending on the PeopleType parameter passed
     *
     * @param peopleType the type of people to be filtered in the select query
     * @return raw query string to select people with the requested filter
     * @see PeopleType
     */
    private String getQueryPeopleByType(PeopleType peopleType) {
        String query = "SELECT * FROM " + PeopleFeedEntry.TABLE_PEOPLE;
        return (peopleType == PeopleType.ALL)
                ? query
                : query + " WHERE " + PeopleFeedEntry.KEY_TYPE + " = '" + peopleType.getId() + "' "
                + " ORDER BY " + PeopleFeedEntry.KEY_ID + " DESC";
    }

    /**
     * Method to get People sub table query
     * depending on the type and userId parameter passed
     *
     * @param peopleTypeId the people type id mapped for PeopleType.TEACHER or PeopleType.STUDENT
     * @param peopleId     the People main id of the persons attribute being retrieved
     * @return raw query string to select people additional attribute
     * with the requested filter for the requested Person
     */
    private String getQueryPeopleSubTableByType(int peopleTypeId, int peopleId) {
        return "SELECT * FROM " +
                ((peopleTypeId == PeopleType.TEACHER.getId()) ? TeacherFeedEntry.TABLE_TEACHER : StudentFeedEntry.TABLE_STUDENT) +
                " WHERE " +
                ((peopleTypeId == PeopleType.TEACHER.getId()) ? TeacherFeedEntry.KEY_PEOPLE_ID : StudentFeedEntry.KEY_PEOPLE_ID) +
                " = '" + peopleId + "' LIMIT 1";
    }
}
