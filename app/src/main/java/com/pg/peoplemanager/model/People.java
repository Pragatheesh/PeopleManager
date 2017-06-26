package com.pg.peoplemanager.model;

import java.io.Serializable;

/**
 * People.java Represents People of any type
 * Abstract and to be extended by different types of People
 *
 * @author PG
 * @version 1.0
 * @see java.io.Serializable
 */
public abstract class People implements Serializable {
    /**
     * The unique id of the person
     */
    private int id;

    /**
     * The first and last name of this person
     */
    private String name;

    /**
     * The age of this person
     */
    private int age;

    /**
     * Constructor to be called from the subclass
     * to initiate values of this persons
     *
     * @param id   the unique id of this person
     * @param name the first and last name of this person
     * @param age  the age of this person
     */
    People(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Constructor to be called from the subclass
     * to create and initiate values of this persons
     *
     * @param name the first and last name of this person
     * @param age  the age of this person
     */
    People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Gets the unique id of this Person.
     *
     * @return this Person's unique id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique id of this Person
     *
     * @param id the id to be set for this person
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first and last name of this Person.
     *
     * @return this Person's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the first and the last name of this Person
     *
     * @param name the Person's name. Should include both first and last name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of this Person.
     *
     * @return this Person's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of this Person
     *
     * @param age the Person's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets this Person's person attributes and their
     * values in a formatted string.
     *
     * @return this Person's attributes and values in a string.
     */
    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
