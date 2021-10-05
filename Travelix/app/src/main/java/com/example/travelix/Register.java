package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.travelix.DBadapter.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextPhone, editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    Button registerUser;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.btn_signUp);
        registerUser.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.et_userName);
        editTextPhone = (EditText) findViewById(R.id.et_userPhone);
        editTextEmail = (EditText) findViewById(R.id.et_userEmail);
        editTextPassword = (EditText) findViewById(R.id.et_userPass);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_signUp:
            registerUser();
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if(phone.isEmpty()){
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return;
        }

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
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(name, phone, email);

                    FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "User successfully registered", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }else{
                                Toast.makeText(Register.this, "Failed to register user", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }else{
                    Toast.makeText(Register.this, "Failed to register user", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}