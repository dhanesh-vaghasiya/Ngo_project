package com.example.ngo_project;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class admin_register extends Fragment {
    Button toUserLoginBtn, adminSignInBtn;
    String adminPassword = "y";

    EditText fieldId, fieldPassword;
    public admin_register() {}

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_register, container, false);

        toUserLoginBtn = view.findViewById(R.id.change_user_login_btn);
        adminSignInBtn = view.findViewById(R.id.admin_sign_in_btn);
        fieldId = view.findViewById(R.id.admin_key_field);


        toUserLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.login_page_fragment, new user_register()).commit();
                requireActivity().getSupportFragmentManager().beginTransaction().remove(new admin_register()).commit();
            }
        });

        adminSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference adminkey = FirebaseDatabase.getInstance().getReference().child("Admin keys");
                adminkey.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String enteredKey = fieldId.getText().toString().trim();

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String adminKeyFromDatabase = String.valueOf(snapshot1.getValue(Long.class));
                            Log.d("keys", String.valueOf(snapshot1.getValue()));

//                             Check if the entered key matches any admin key from the database
                            if (enteredKey.equals(adminKeyFromDatabase)) {
                                SharedPreferences preferences = getActivity().getSharedPreferences("AdminPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();

                                editor.putBoolean("isAdminLoggedIn", true);
                                editor.apply();
                                Toast.makeText(getActivity(), "LOgin success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }

}