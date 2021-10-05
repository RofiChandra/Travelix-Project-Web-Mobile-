package com.example.travelix.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.travelix.BookingForm;
import com.example.travelix.R;

public class RoomClassic extends AppCompatActivity {

    Button btnBookClassic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_classic);

        btnBookClassic = (Button) findViewById(R.id.btn_bookClassic);
        btnBookClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomClassic.this, BookingForm.class));
            }
        });
    }
}