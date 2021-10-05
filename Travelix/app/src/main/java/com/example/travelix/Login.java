package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText editTextEmail, editTextPassword;
    TextView register, forgotPassword;
    Button logIn;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.tv_register);
        register.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.tv_forgotPassword);
        forgotPassword.setOnClickListener(this);

        logIn = (Button) findViewById(R.id.btn_login);
        logIn.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        editTextEmail = (EditText) findViewById(R.id.et_loginEmail);
        editTextPassword = (EditText) findViewById(R.id.et_loginPass);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.tv_forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;

            case R.id.btn_login:
                userLogin();
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email Address is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please input a valid Email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6 ){
            editTextPassword.setError("Minimal password length is 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else{
                    Toast.makeText(Login.this, "Failed to login please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}