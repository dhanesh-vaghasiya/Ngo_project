package com.example.ngo_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
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
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login_Activity extends AppCompatActivity {
    Button toSignUpPageBtn, resetPassword;
    ImageButton loginBtn;
    EditText emailEdt, passwordEdt;
    private FirebaseAuth mAuth;
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toSignUpPageBtn = findViewById(R.id.to_sign_up_page_btn);
        resetPassword = findViewById(R.id.forget_password_btn);
        emailEdt = findViewById(R.id.email_edt);
        passwordEdt = findViewById(R.id.password_edt);
        loginBtn = findViewById(R.id.login_btn);

        mAuth = FirebaseAuth.getInstance();
        

        toSignUpPageBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUp.class));
            
        });

        loginBtn.setOnClickListener(v -> {
            mAuth.signInWithEmailAndPassword(emailEdt.getText().toString(), passwordEdt.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Login_Activity.this, "login fails", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        resetPassword.setOnClickListener(v -> {
            if (emailEdt.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your email address", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.sendPasswordResetEmail(emailEdt.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Activity.this, "Password reset email is sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}