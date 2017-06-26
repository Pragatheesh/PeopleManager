package com.pg.peoplemanager.adapter;

import com.pg.peoplemanager.model.People;

/**
 * AdapterActivityCommunicator - Interface used to
 * perform/pass data operation actions with necessary data
 * between adaptor and the activity
 *
 * @author PG
 * @version 1.0
 */
public interface AdapterActivityCommunicator {
    /**
     * Invoke a delete action on the adaptor
     * to the relevant activity method
     *
     * @param id the unique id of the Person requested to be deleted
     */
    void respondDelete(int id);

    /**
     * Invoke a edit person request to the
     * activity with the details of the Person
     *
     * @param person the People object to be requested to edit
     */
    void editRequest(People person);
}
