package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelix.DBadapter.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {

    EditText etProfileName, etProfilePhone;
    Button btnEdit, btnBack;

    FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference DBreference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        etProfileName = findViewById(R.id.et_editName);
        etProfilePhone = findViewById(R.id.et_editPhone);

        btnEdit = findViewById(R.id.btn_edit);
        btnBack = findViewById(R.id.btn_back);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfileData(currentUser);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        DBreference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        DBreference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String name = userProfile.name;
                    String phone = userProfile.phone;

                    etProfileName.setText(name);
                    etProfilePhone.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditProfile.this, "Something wrong i can feel it", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateProfileData(FirebaseUser currentUser) {
        String nameChanged = etProfileName.getText().toString();
        String phoneChanged = etProfilePhone.getText().toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.child(userID).child("name").setValue(nameChanged);
        ref.child(userID).child("phone").setValue(phoneChanged);
    }
}