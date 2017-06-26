package com.pg.peoplemanager.model;

import android.util.Pair;

/**
 * PeopleAttribute.java - Interface to get the additional attribute of People.
 *
 * @author PG
 * @version 1.0
 */
public interface PeopleAttribute {

    /**
     * Dynamically get the defined additional attribute of People
     * depending on their type
     *
     * @return pair of key indicating the label of the property
     * and value as the actual value of the additional attribute
     * @see Pair
     */
    Pair<String, String> getAdditionalAttribute();
}
