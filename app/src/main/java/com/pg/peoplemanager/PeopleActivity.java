package com.pg.peoplemanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.pg.peoplemanager.adapter.AdapterActivityCommunicator;
import com.pg.peoplemanager.adapter.PeopleAdapter;
import com.pg.peoplemanager.fragment.PeopleCreateCommunicator;
import com.pg.peoplemanager.fragment.PeopleCreateFragment;
import com.pg.peoplemanager.model.People;
import com.pg.peoplemanager.model.Student;
import com.pg.peoplemanager.model.Teacher;
import com.pg.peoplemanager.repository.PeopleRepository;
import com.pg.peoplemanager.util.PeopleType;

/**
 * PeopleActivity.java - The main activity for managing the People
 * Lists the people and provides other interactions on the
 * people resources
 *
 * @author PG
 * @version 1.0
 * @see AppCompatActivity
 * @see PeopleCreateCommunicator
 * @see AdapterActivityCommunicator
 */
public class PeopleActivity extends AppCompatActivity implements PeopleCreateCommunicator, AdapterActivityCommunicator {

    /**
     * The adapter to handle the list of People
     * on the Recycler View
     */
    private PeopleAdapter mAdapter;

    /**
     * The people repository reference to
     * perform data operations on People
     */
    private PeopleRepository mPeopleRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        //Toolbar initialization
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FAB initialization
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment createPeople = new PeopleCreateFragment();
                Bundle args = new Bundle();
                args.putSerializable("person", null);
                createPeople.setArguments(args);
                createPeople.show(getSupportFragmentManager(), "createPeople");
            }
        });

        //Initialize views
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.people_list);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mPeopleRepository = new PeopleRepository(this);

        mAdapter = new PeopleAdapter(PeopleActivity.this,
                mPeopleRepository.getAllPeople(PeopleType.ALL));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * {@inheritDoc}
     *
     * @param person the person details who was created for further actions
     */
    @Override
    public void respondCreate(People person) {
        if (person instanceof Teacher)
            mPeopleRepository.create((Teacher) person);
        else if (person instanceof Student)
            mPeopleRepository.create((Student) person);

        mAdapter.swap(mPeopleRepository.getAllPeople(PeopleType.ALL));

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.create_success), Toast.LENGTH_SHORT).show();
    }

    /**
     * {@inheritDoc}
     *
     * @param person the person details who was updated for further actions
     */
    @Override
    public void respondUpdate(People person) {
        if (person instanceof Teacher)
            mPeopleRepository.update((Teacher) person);
        else if (person instanceof Student)
            mPeopleRepository.update((Student) person);

        mAdapter.swap(mPeopleRepository.getAllPeople(PeopleType.ALL));

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.update_success), Toast.LENGTH_SHORT).show();
    }

    /**
     * {@inheritDoc}
     *
     * @param id the unique id of the Person requested to be deleted
     */
    @Override
    public void respondDelete(int id) {
        mPeopleRepository.delete(id);
        mAdapter.swap(mPeopleRepository.getAllPeople(PeopleType.ALL));

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.delete_success), Toast.LENGTH_SHORT).show();
    }

    /**
     * {@inheritDoc}
     *
     * @param person the People object to be requested to edit
     */
    @Override
    public void editRequest(People person) {
        DialogFragment createPeople = new PeopleCreateFragment();
        Bundle args = new Bundle();
        args.putSerializable("person", person);
        createPeople.setArguments(args);
        createPeople.show(getSupportFragmentManager(), "editPeople");
    }
}
