package com.pg.peoplemanager.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pg.peoplemanager.R;
import com.pg.peoplemanager.model.People;
import com.pg.peoplemanager.model.PeopleAttribute;
import com.pg.peoplemanager.model.Student;
import com.pg.peoplemanager.model.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * PeopleAdapter.java - Recycler View Adapter for the People View
 *
 * @author PG
 * @version 1.0
 * @see RecyclerView.Adapter
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    /**
     * The adaptor reference for the list of People
     * bound to the Recycler View
     *
     * @see People
     */
    private final List<People> mPeopleList;

    /**
     * AdapterActivityCommunicator interface reference variable
     * to dynamically invoke the activity methods in relation
     *
     * @see AdapterActivityCommunicator
     */
    private final AdapterActivityCommunicator mActivity;

    /**
     * Create a new People adapter
     *
     * @param mActivity   the activity holding the recycler view
     * @param mPeopleList the list of people for this adapter to hold
     * @see People
     */
    public PeopleAdapter(Activity mActivity, List<People> mPeopleList) {
        this.mPeopleList = mPeopleList;
        this.mActivity = (AdapterActivityCommunicator) mActivity;
    }

    @Override
    public PeopleAdapter.PeopleViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View simpleRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_people, parent, false);

        return new PeopleViewHolder(simpleRow);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        final People person;
        PeopleAttribute personAttribute;

        //Bind the attributes of the user to the relevant views
        if (mPeopleList.get(position) instanceof Teacher) {
            person = mPeopleList.get(position);
            personAttribute = (Teacher) mPeopleList.get(position);
        } else {
            person = mPeopleList.get(position);
            personAttribute = (Student) mPeopleList.get(position);
        }

        holder.mName.setText(person.getName());

        //Passed as single string literal to avoid Internalization issues
        String ageDisplayed = "AGE : " + person.getAge();
        holder.mAge.setText(ageDisplayed);

        String additionalAttributeDisplayed = personAttribute.getAdditionalAttribute().first + personAttribute.getAdditionalAttribute().second;
        holder.mAttribute.setText(additionalAttributeDisplayed);

        //Click listener for the buttons
        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.editRequest(person);
            }
        });

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.respondDelete(person.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }

    /**
     * Use to update the People list within the adaptor
     * and notify the adapter of data set changes
     *
     * @param people the new list of people to be updated within the adapter
     */
    public void swap(ArrayList<People> people) {
        mPeopleList.clear();
        mPeopleList.addAll(people);
        notifyDataSetChanged();
    }

    /**
     * The view holder for the RecyclerView
     *
     * @see RecyclerView.ViewHolder
     */
    public static class PeopleViewHolder extends RecyclerView.ViewHolder {

        //Views within the view holder
        public final TextView mName;
        public final TextView mAge;
        public final TextView mAttribute;
        private final ImageButton mEdit;
        private final ImageButton mDelete;

        /**
         * Constructor to initialize and bin view to their resource
         *
         * @param v the view
         */
        public PeopleViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.li_p_name);
            mAge = (TextView) v.findViewById(R.id.li_p_age);
            mAttribute = (TextView) v.findViewById(R.id.li_p_attribute);

            mEdit = (ImageButton) v.findViewById(R.id.li_p_edit);
            mDelete = (ImageButton) v.findViewById(R.id.li_p_delete);
        }
    }
}
