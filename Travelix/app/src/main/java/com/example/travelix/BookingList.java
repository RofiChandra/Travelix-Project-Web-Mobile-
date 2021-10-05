package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.travelix.DBadapter.BookingAdapter;
import com.example.travelix.DBadapter.BookingModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookingList extends AppCompatActivity {
    BookingAdapter bookingAdapter;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<BookingModel> listBooking;
    RecyclerView tvTampil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        tvTampil = findViewById(R.id.tv_tampil);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tvTampil.setLayoutManager(mLayout);
        tvTampil.setItemAnimator(new DefaultItemAnimator());
        showData();
        

        // Initialize and Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.booking);

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

    private void showData() {
        database.child("Booking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listBooking = new ArrayList<>();
                for(DataSnapshot item : snapshot.getChildren()){
                    BookingModel bookingModel = item.getValue(BookingModel.class);
                    bookingModel.setKey(item.getKey());
                    listBooking.add(bookingModel);
                }
                bookingAdapter = new BookingAdapter(listBooking, BookingList.this);
                tvTampil.setAdapter(bookingAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}