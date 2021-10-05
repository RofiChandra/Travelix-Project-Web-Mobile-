package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.travelix.Room.RoomClassic;
import com.example.travelix.Room.RoomClub;
import com.example.travelix.Room.RoomDeluxe;
import com.example.travelix.Room.RoomPremiumDeluxe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Hotel extends AppCompatActivity implements View.OnClickListener {

    Button classic, deluxe,premium, international;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        classic = (Button)findViewById(R.id.btn_roomClassic);
        classic.setOnClickListener(this);

        deluxe = (Button)findViewById(R.id.btn_roomDeluxe);
        deluxe.setOnClickListener(this);

        premium = (Button)findViewById(R.id.btn_roomPremium);
        premium.setOnClickListener(this);

        international = (Button)findViewById(R.id.btn_roomInter);
        international.setOnClickListener(this);

        // Initialize and Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.hotel);

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
                        return true;
                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(), BookingList.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_roomClassic:
                startActivity(new Intent(this, RoomClassic.class));
                break;

            case R.id.btn_roomDeluxe:
                startActivity(new Intent(this, RoomDeluxe.class));
                break;

            case R.id.btn_roomPremium:
                startActivity(new Intent(this, RoomPremiumDeluxe.class));
                break;

            case R.id.btn_roomInter:
                startActivity(new Intent(this, RoomClub.class));
                break;
        }
    }
}