package com.example.ngo_project;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class user_page_child_adoption extends Fragment {
    public user_page_child_adoption() {}

    Button toFormBtn, formSubmitBtn;
    
    ScrollView scrollInfo;
    Spinner genderDropDown, maritalStatusDropDown, childGenderDropDown, childAgeGroupDropDown;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_page_child_adoption, container);


//        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.form_pages_container,new form_personal_data()).commit();

//        ArrayAdapter<CharSequence>adapterGender = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<CharSequence>adapterMaritalStatus = ArrayAdapter.createFromResource(getContext(), R.array.marital_status, android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<CharSequence>adapterChildAgeGroup = ArrayAdapter.createFromResource(getContext(), R.array.child_age_group, android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<CharSequence>adapterChildGender = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_spinner_dropdown_item);

//        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        genderDropDown.setAdapter(adapterGender);
//
//        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        maritalStatusDropDown.setAdapter(adapterMaritalStatus);
//
//        adapterChildGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        childGenderDropDown.setAdapter(adapterChildGender);
//
//        adapterChildAgeGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        childAgeGroupDropDown.setAdapter(adapterChildAgeGroup);

//        toFormBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scrollInfo.removeAllViews();
////                view.findViewById(R.id.child_adoption_form_container).setVisibility(View.VISIBLE);
//            }
//        });
//
//        formSubmitBtn.setOnClickListener(v -> {
//            boolean pass = true;
//            /*
//            * Checkin       g all the contraints in the form
//            *
//            *
//            *
//            * */
//


        //********************************************* User form submit process *******************************************************************

//            if (true){
//                User_adoption_request user = new User_adoption_request();
//                user.setFullName("Dhanesh Vaghasiya");
//                user.setOccupation("Actor");
//                user.setGender("male");
//                user.setDateOfBirth("12/4/2005");
//                user.setMeritalStatus("married");
//                user.setHouseNumber("304-j");
//                user.setSociety("Mangalam residency");
//                user.setLandmark("ashadeep school");
//                user.setDistrict("surat");
//                user.setState("gujarat");
//                user.setPincode("395006");
//                user.setEmail("dhaneshvaghasiya999@gmail.com");
//                user.setMobileNo("9265944501");
//                user.setSecondaryMobileNo("9106068925");
//                user.setAddharid("366172044852");
//                user.setChildGender("female");
//                user.setAgeGroup("1-4");
//                user.setAge("28");
//
//
//                FirebaseAuth auth = FirebaseAuth.getInstance();
//                FirebaseUser currentUser = auth.getCurrentUser();
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//                user.setUid(currentUser.getUid());
//
//                DocumentReference docRef = db.collection("users").document(currentUser.getUid());
//                        docRef.set(user)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(getContext(), "user added with id : "+currentUser.getUid(), Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getContext(), "user faiiled to add", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });

        return view;
    }

}