package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelix.DBadapter.User;
import com.example.travelix.Room.RoomClassic;
import com.example.travelix.Room.RoomClub;
import com.example.travelix.Room.RoomPremiumDeluxe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button classic, premium, clubInter;

    private FirebaseUser user;
    private DatabaseReference DBreference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classic = (Button) findViewById(R.id.btn_book1);
        classic.setOnClickListener(this);

        premium = (Button) findViewById(R.id.btn_book2);
        premium.setOnClickListener(this);

        clubInter = (Button) findViewById(R.id.btn_book3);
        clubInter.setOnClickListener(this);


        // Initialize and Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.hotel:
                        startActivity(new Intent(getApplicationContext(), Hotel.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(), BookingList.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        DBreference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        final TextView nameBanner = (TextView) findViewById(R.id.tv_banner);

        DBreference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String name = userProfile.name;

                    nameBanner.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something wrong i can feel it", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_book1:
                startActivity(new Intent(this, RoomClassic.class));
                break;

            case R.id.btn_book2:
                startActivity(new Intent(this, RoomPremiumDeluxe.class));
                break;

            case R.id.btn_book3:
                startActivity(new Intent(this, RoomClub.class));
                break;
        }
    }
}
