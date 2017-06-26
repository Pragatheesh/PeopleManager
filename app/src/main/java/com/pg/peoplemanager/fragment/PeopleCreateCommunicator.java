package com.pg.peoplemanager.fragment;

import com.pg.peoplemanager.model.People;

/**
 * PeopleCreateCommunicator.java - Fragment Communicator pattern implementation.
 * interface to decoupled interactions on the people create fragment
 *
 * @author PG
 * @version 1.0
 */
public interface PeopleCreateCommunicator {

    /**
     * Invoked when CREATE is made on the fragment with the attributes
     * of the person for further handling
     *
     * @param person the person details who was created for further actions
     */
    void respondCreate(People person);

    /**
     * Invoked when a UPDATE is made on the fragment with the attributes
     * of the person for further handling
     *
     * @param person the person details who was updated for further actions
     */
    void respondUpdate(People person);
}
