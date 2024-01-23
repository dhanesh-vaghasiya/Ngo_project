package com.example.ngo_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.auth.FirebaseAuth;

public class user_sign_up_form extends Fragment {

   EditText userSignUpFirstName, userSignUpLastName, userSignUpEmail, userSignUpPassword, userSignUpConfirmPassword;
   Button signUpBtn;

    private FirebaseAuth mAuth;
    public user_sign_up_form() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_user_sign_up_form, container, false);

        userSignUpFirstName = view.findViewById(R.id.user_first_name_field);
        userSignUpLastName = view.findViewById(R.id.user_last_name_field);
        userSignUpEmail = view.findViewById(R.id.user_email_field);
        userSignUpPassword = view.findViewById(R.id.user_password_field);
        userSignUpConfirmPassword = view.findViewById(R.id.user_confirm_password_field);
        signUpBtn = view.findViewById(R.id.user_sign_up_btn);

        mAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("TAG", );
                if (userSignUpPassword.getText().toString().equals(userSignUpConfirmPassword.getText().toString())){
//                  firebase authentication
                    mAuth.createUserWithEmailAndPassword(userSignUpEmail.getText().toString(), userSignUpPassword.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getContext(), "login success", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getContext(), MainActivity.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                } else {
                    Toast.makeText(getContext(), "nothing happend", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public View getView(){
        return this.view;
    }
}