package com.pg.peoplemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pg.peoplemanager.R;
import com.pg.peoplemanager.model.Student;
import com.pg.peoplemanager.model.Teacher;

/**
 * PeopleCreateFragment.java - The fragment used to either
 * CREATE or UPDATE a People of any type
 * Prime responsibility is to provide interface to display details
 * of the passed Person and to invoke relevant methods on interactions
 *
 * @author PG
 * @version 1.0
 * @see DialogFragment
 */
public class PeopleCreateFragment extends DialogFragment {

    /**
     * PeopleCreateCommunicator interface reference variable
     * to dynamically invoke the activity in relation
     *
     * @see PeopleCreateCommunicator
     */
    private PeopleCreateCommunicator com;

    //Views within the fragments
    private TextView mTitle;
    private Spinner mPeopleType;
    private EditText mName, mAge, mPeopleAttribute;

    private int userId;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*
          Reference the activity invoking the fragment
          to pass back interaction details voa the interface methods
         */
        com = (PeopleCreateCommunicator) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_create_edit_people, container, false);
        if (getDialog().getWindow() != null)
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        /*
          Initialize all the views of the fragment
         */
        mTitle = (TextView) v.findViewById(R.id.title);
        mPeopleType = (Spinner) v.findViewById(R.id.type);
        TextView mPeopleTypeLabel = (TextView) v.findViewById(R.id.type_label);
        mName = (EditText) v.findViewById(R.id.name);
        mAge = (EditText) v.findViewById(R.id.age);
        mPeopleAttribute = (EditText) v.findViewById(R.id.attribute);
        Button mCreate = (Button) v.findViewById(R.id.create_people);
        Button mEdit = (Button) v.findViewById(R.id.edit_people);

        /*
          Check arguments and if available, set value for edit
         */
        if (getArguments().getSerializable("person") != null) {
            if (getArguments().getSerializable("person") instanceof Teacher) {
                //If teacher initiate the UI components with supplied data
                final Teacher teacher = (Teacher) getArguments().getSerializable("person");
                mPeopleType.setSelection(0);
                if (teacher != null) {
                    userId = teacher.getId();
                    mName.setText(teacher.getName());
                    mAge.setText(String.valueOf(teacher.getAge()));
                    mPeopleAttribute.setText(teacher.getTeachingGrade());
                }
                mTitle.setText(getActivity().getResources().getString(R.string.edit_teacher));
            } else if (getArguments().getSerializable("person") instanceof Student) {
                //If student initiate the UI components with supplied data
                final Student student = (Student) getArguments().getSerializable("person");
                mPeopleType.setSelection(1);
                if (student != null) {
                    userId = student.getId();
                    mName.setText(student.getName());
                    mAge.setText(String.valueOf(student.getAge()));
                    mPeopleAttribute.setText(student.getFavouriteSubject());
                }
                mTitle.setText(getActivity().getResources().getString(R.string.edit_student));
            }

            //Hide the people type selection spinner and the create button if an edit request is made
            mCreate.setVisibility(View.GONE);
            mPeopleTypeLabel.setVisibility(View.GONE);
            mPeopleType.setVisibility(View.GONE);
        } else {
            //Hide the edit button if the UI is to create
            mEdit.setVisibility(View.GONE);
        }

        /*
          OnItemSelected listener people type
          Handle the view changes depending on the people type chosen
         */
        mPeopleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mPeopleType.getItemAtPosition(position).toString().equals(getActivity().getResources().getString(R.string.teacher))) {
                    mPeopleAttribute.setHint(getActivity().getResources().getString(R.string.teaches_grade));
                } else if (mPeopleType.getItemAtPosition(position).toString().equals(getActivity().getResources().getString(R.string.student))) {
                    mPeopleAttribute.setHint(getActivity().getResources().getString(R.string.favourite_subject));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*
          OnClick listener for CREATE button
          Once clicked, call back the create person method in the calling
          activity via the interface with the Person of correct type for
          further processing
         */
        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPeopleType.getSelectedItem().toString().equals(getActivity().getResources().getString(R.string.teacher))) {
                    com.respondCreate(new Teacher(mName.getText().toString(), Integer.parseInt(mAge.getText().toString()), mPeopleAttribute.getText().toString()));
                } else if (mPeopleType.getSelectedItem().toString().equals(getActivity().getResources().getString(R.string.student))) {
                    com.respondCreate(new Student(mName.getText().toString(), Integer.parseInt(mAge.getText().toString()), mPeopleAttribute.getText().toString()));
                }

                dismiss();
            }
        });

        /*
          OnClick listener for UPDATE button
          Once clicked, call back the update person method in the calling
          activity via the interface with the Person of correct type for
          further processing
         */
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTitle.getText().toString().equals(getActivity().getResources().getString(R.string.edit_teacher))) {
                    com.respondUpdate(new Teacher(userId, mName.getText().toString(), Integer.parseInt(mAge.getText().toString()), mPeopleAttribute.getText().toString()));
                } else if (mTitle.getText().toString().equals(getActivity().getResources().getString(R.string.edit_student))) {
                    com.respondUpdate(new Student(userId, mName.getText().toString(), Integer.parseInt(mAge.getText().toString()), mPeopleAttribute.getText().toString()));
                }

                dismiss();
            }
        });

        return v;
    }
}
