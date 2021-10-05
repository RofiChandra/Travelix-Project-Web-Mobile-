package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelix.DBadapter.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    Button btnEdit, btnSignOut;

    private FirebaseUser user;
    private DatabaseReference DBreference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        btnEdit = findViewById(R.id.btn_editProfile);
        btnSignOut = findViewById(R.id.btn_signOut);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                startActivity(i);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, Login.class));
            }
        });

        // Initialize and Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.hotel:
                        startActivity(new Intent(getApplicationContext(), Hotel.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(), BookingList.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        DBreference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        final TextView nameTextView = (TextView) findViewById(R.id.tv_profileName);
        final TextView phoneTextView = (TextView) findViewById(R.id.tv_profilePhone);
        final TextView emailTextView = (TextView) findViewById(R.id.tv_profileEmail);

        DBreference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile !=null){
                    String name = userProfile.name;
                    String phone = userProfile.phone;
                    String email = userProfile.email;

                    nameTextView.setText(name);
                    phoneTextView.setText(phone);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something wrong i can feel it", Toast.LENGTH_LONG).show();
            }
        });




    }
}