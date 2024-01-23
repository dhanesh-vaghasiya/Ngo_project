package com.example.ngo_project;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;


public class user_page_child_adoption extends Fragment {
    public user_page_child_adoption() {}

    Button toFormBtn;
    ScrollView scrollInfo;
    Spinner genderDropDown, maritalStatusDropDown, childGenderDropDown, childAgeGroupDropDown;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_page_child_adoption, container, false);

        toFormBtn = view.findViewById(R.id.to_form_btn);
        scrollInfo = view.findViewById(R.id.info_scroll);

        genderDropDown = view.findViewById(R.id.gender_Drop_down);
        maritalStatusDropDown = view.findViewById(R.id.marital_status_drop_down);
        childAgeGroupDropDown = view.findViewById(R.id.child_age_group_drop_down);
        childGenderDropDown = view.findViewById(R.id.child_gender_drop_down);

        ArrayAdapter<CharSequence>adapterGender = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence>adapterMaritalStatus = ArrayAdapter.createFromResource(getContext(), R.array.marital_status, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence>adapterChildAgeGroup = ArrayAdapter.createFromResource(getContext(), R.array.child_age_group, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence>adapterChildGender = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_spinner_dropdown_item);

        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDropDown.setAdapter(adapterGender);

        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalStatusDropDown.setAdapter(adapterMaritalStatus);

        adapterChildGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childGenderDropDown.setAdapter(adapterChildGender);

        adapterChildAgeGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childAgeGroupDropDown.setAdapter(adapterChildAgeGroup);

        toFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollInfo.removeAllViews();
                view.findViewById(R.id.child_adoption_form_container).setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

//    public void loadDropDowns(View view) {
//    }
}