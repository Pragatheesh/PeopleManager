package com.pg.peoplemanager.model;

import android.util.Pair;

/**
 * Student.java Represents People of type Student
 * Describes the the attributes and actions of a Student
 *
 * @author PG
 * @version 1.0
 * @see People
 * @see PeopleAttribute
 */
public class Student extends People implements PeopleAttribute {

    /**
     * The favourite subject of this Student
     */
    private String favouriteSubject;

    /**
     * Create a new Student with the given attributes
     *
     * @param name             the first and last name of this Student
     * @param age              the age of this Student
     * @param favouriteSubject the favourite subject of this Student
     */
    public Student(String name, int age, String favouriteSubject) {
        super(name, age);
        this.favouriteSubject = favouriteSubject;
    }

    /**
     * Create a new Student with the given attributes
     *
     * @param id               the unique id of this Student
     * @param name             the first and last name of this Student
     * @param age              the age of this Student
     * @param favouriteSubject the favourite subject of this Student
     */
    public Student(int id, String name, int age, String favouriteSubject) {
        super(id, name, age);
        this.favouriteSubject = favouriteSubject;
    }

    /**
     * Get the favourite subject of this Student
     *
     * @return the favourite subject of this Student
     */
    public String getFavouriteSubject() {
        return favouriteSubject;
    }

    /**
     * Change the favourite subject of this Student
     *
     * @param favouriteSubject the favourite subject of a Student
     */
    public void setFavouriteSubject(String favouriteSubject) {
        this.favouriteSubject = favouriteSubject;
    }

    /**
     * {@inheritDoc}
     * In this case, returns the favourite subject of the Student
     */
    @Override
    public Pair<String, String> getAdditionalAttribute() {
        return new Pair<>("FAVOURITE SUBJECT : ", this.getFavouriteSubject());
    }
}
