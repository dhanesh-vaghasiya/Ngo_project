package com.example.ngo_project;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

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

public class user_register extends Fragment {
    public user_register() {
        // Required empty public constructor
    }
    Button adminLoginBtn;

    Button signInBtn, signUpBtn, toSignInBtn, toSignUpBtn;
    FrameLayout userLoginFormContainer;
    View toggleLoginMode;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_register, container, false);
        adminLoginBtn = view.findViewById(R.id.change_user_login_btn);
        signInBtn = view.findViewById(R.id.user_sign_in_btn);


        toSignInBtn = view.findViewById(R.id.login_page_sign_in_btn);
        toSignUpBtn = view.findViewById(R.id.login_page_sign_up_btn);
        toggleLoginMode = view.findViewById(R.id.toggle_login_mode_view);
        userLoginFormContainer = view.findViewById(R.id.user_login_form_container);






        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.user_login_form_container ,new user_sign_in_form()).commit();
        toSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: dsf" );
                ObjectAnimator translateX = ObjectAnimator.ofFloat(toggleLoginMode, "translationX", 0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(translateX);
                animatorSet.setDuration(500);
                animatorSet.start();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.user_login_form_container ,new user_sign_in_form()).commit();
            }
        });

        toSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator translateX = ObjectAnimator.ofFloat(toggleLoginMode, "translationX", 180f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(translateX);
                animatorSet.setDuration(500);
                animatorSet.start();
                Log.e("TAG", "onClick: dsf" );
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.user_login_form_container ,new user_sign_up_form()).commit();
            }
        });

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.login_page_fragment, new admin_register()).commit();
                requireActivity().getSupportFragmentManager().beginTransaction().remove(new user_register()).commit();

            }
        });

//        Log.d("TAG", userSignUpConfirmPassword.getText().toString());
//        signInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////                mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
////                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
////                            @Override
////                            public void onComplete(@NonNull Task<AuthResult> task) {
////                                if (task.isSuccessful()) {
////                                    // Sign in success, update UI with the signed-in user's information
//////                                    Log.d(TAG, "createUserWithEmail:success");
////                                    FirebaseUser user = mAuth.getCurrentUser();
////
////                                }
////                            }
////                        });
//            }
//        });




        return view;
    }


}