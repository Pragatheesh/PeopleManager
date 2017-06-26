package com.pg.peoplemanager.util;

/**
 * PeopleType.java - People types that can be used
 *
 * @author PG
 * @version 1.0
 */
public enum PeopleType {
    /**
     * All the types of People
     */
    ALL(0),

    /**
     * People of Type Teacher
     */
    TEACHER(1),

    /**
     * People of Type Student
     */
    STUDENT(2);

    private final int id;

    PeopleType(int id) {
        this.id = id;
    }

    /**
     * Get the mapped id for each People Type
     *
     * @return returns the id of the relevant PeopleType
     */
    public int getId() {
        return id;
    }
}
