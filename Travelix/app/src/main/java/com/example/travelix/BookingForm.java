package com.example.travelix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.travelix.DBadapter.BookingModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookingForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnBook;
    EditText etGuestName ,etGuestPhone ,etGuestEmail ,etCheckIn, etCheckout, etCardNumber,EtCardHolder,etCardExpired, etCardCVC;
    DatePickerDialog.OnDateSetListener setListener;
    Spinner spinner, spinner1;

    DatabaseReference DBreferences = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        etGuestName = findViewById(R.id.et_guestName);
        etGuestPhone = findViewById(R.id.et_guestPhone);
        etGuestEmail = findViewById(R.id.et_guestEmail);
        etCardNumber = findViewById(R.id.et_cardNumber);
        EtCardHolder = findViewById(R.id.et_cardHolder);
        etCardExpired = findViewById(R.id.et_cardExpired);
        etCardCVC = findViewById(R.id.et_cardCVC);

        spinner1 = findViewById(R.id.spn_tripType);
        spinner = findViewById(R.id.spn_roomType);


        etCheckIn = (EditText) findViewById(R.id.et_checkIn);
        etCheckout = (EditText) findViewById(R.id.et_checkOut);

        btnBook = findViewById(R.id.btn_pesan);

        String key = DBreferences.push().getKey();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getRoomType = spinner.getSelectedItem().toString();
                String getGuestName = etGuestName.getText().toString();
                String getGuestPhone = etGuestPhone.getText().toString();
                String getGuestEmail = etGuestEmail.getText().toString();
                String getCheckIn = etCheckIn.getText().toString();
                String getCheckOut = etCheckout.getText().toString();
                String getTripType = spinner1.getSelectedItem().toString();
                String setKey = key.toString();
                if(getGuestName.isEmpty()){
                    etGuestName.setError("Please input guest name!");
                }else if(getGuestEmail.isEmpty()){
                    etGuestEmail.setError("Email is required");
                }else{
                    DBreferences.child("Booking").push().setValue(new BookingModel(getRoomType,getGuestName,getGuestPhone,getGuestEmail,getCheckIn,getCheckOut,getTripType, setKey)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(BookingForm.this, "Booking successfully submitted", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BookingForm.this, "Booking failed, Please Check again!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookingForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        etCheckIn.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        etCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookingForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        etCheckout.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        Spinner spinner = findViewById(R.id.spn_roomType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.room_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = findViewById(R.id.spn_tripType);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.trip_type, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}