package com.pg.peoplemanager.model;

import android.util.Pair;

/**
 * Teacher.java Represents People of type Teacher
 * Describes the the attributes and actions of a Teacher
 *
 * @author PG
 * @version 1.0
 * @see People
 * @see PeopleAttribute
 */
public class Teacher extends People implements PeopleAttribute {

    /**
     * The grade the Teacher lectures
     */
    private String teachingGrade;

    /**
     * Create a new Teacher with the given attributes
     *
     * @param name          the first and last name of this Teacher
     * @param age           the age of this Teacher
     * @param teachingGrade the grade this Teacher teaches
     */
    public Teacher(String name, int age, String teachingGrade) {
        super(name, age);
        this.setTeachingGrade(teachingGrade);
    }

    /**
     * Create a new Teacher with the given attributes
     *
     * @param id            the unique id of this Teacher
     * @param name          the first and last name of this Teacher
     * @param age           the age of this Teacher
     * @param teachingGrade the grade this Teacher teaches
     */
    public Teacher(int id, String name, int age, String teachingGrade) {
        super(id, name, age);
        this.setTeachingGrade(teachingGrade);
    }

    /**
     * {@inheritDoc}
     * In this case, returns the teaching grade of this Teacher
     */
    @Override
    public Pair<String, String> getAdditionalAttribute() {
        return new Pair<>("TEACHES GRADE : ", this.getTeachingGrade());
    }

    /**
     * Get the grade to which this Teacher teaches
     *
     * @return the teaching grade of this Teacher
     */
    public String getTeachingGrade() {
        return teachingGrade;
    }

    /**
     * Change the teaching grade of this Teacher
     *
     * @param teachingGrade the teaching grade of a Teacher
     */
    private void setTeachingGrade(String teachingGrade) {
        this.teachingGrade = teachingGrade;
    }
}
