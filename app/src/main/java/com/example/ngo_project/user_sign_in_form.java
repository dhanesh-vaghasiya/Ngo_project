package com.example.ngo_project;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class user_sign_in_form extends Fragment {

    EditText userSignInEmail, userSignInPassword;
    Button signInBtn;
    private FirebaseAuth mAuth;

    public user_sign_in_form() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_sign_in_form, container, false);
        userSignInEmail = view.findViewById(R.id.user_sign_in_email);
        userSignInPassword = view.findViewById(R.id.user_sign_in_password);
        signInBtn = view.findViewById(R.id.user_sign_in_btn);

        mAuth = FirebaseAuth.getInstance();


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(userSignInEmail.getText().toString(), userSignInPassword.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "login success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(), MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return view;
    }

}