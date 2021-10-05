package com.example.travelix;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.travelix.DBadapter.BookingModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DialogFormBooking extends DialogFragment implements AdapterView.OnItemSelectedListener {
    private String roomType;
    private String guestName;
    private String guestPhone;
    private String guestEmail;
    private String checkIn;
    private String checkOut;
    private String tripType;
    private String key;
    private String pilih;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public DialogFormBooking(String roomType, String guestName, String guestPhone, String guestEmail, String checkIn, String checkOut, String tripType, String key, String pilih) {
        this.roomType = roomType;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.guestEmail = guestEmail;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tripType = tripType;
        this.key = key;
        this.pilih = pilih;




    }
    String bookingKey  = FirebaseDatabase.getInstance().getReference().child("Booking").push().getKey();
    TextView tNamaTamu, tNomorTamu, tEmailTamu, tTglCheckIn, tTglCheckIOut;
    Spinner sTipeKamar, sTipeTrip;
    Button btnSimpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_booking_form, container, false);
        sTipeKamar = view.findViewById(R.id.spn_roomType);
        tNamaTamu = view.findViewById(R.id.et_guestName);
        tNomorTamu = view.findViewById(R.id.et_guestPhone);
        tEmailTamu = view.findViewById(R.id.et_guestEmail);
        tTglCheckIn = view.findViewById(R.id.et_checkIn);
        tTglCheckIOut = view.findViewById(R.id.et_checkOut);
        sTipeTrip = view.findViewById(R.id.spn_tripType);
        btnSimpan = view.findViewById(R.id.btn_pesan);

        tNamaTamu.setText(guestName);
        tNomorTamu.setText(guestPhone);
        tEmailTamu.setText(guestEmail);
        tTglCheckIn.setText(checkIn);
        tTglCheckIOut.setText(checkOut);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Spinner spinner = view.findViewById(R.id.spn_roomType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.room_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = view.findViewById(R.id.spn_tripType);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.trip_type, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        tTglCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        tTglCheckIn.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        tTglCheckIOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        tTglCheckIOut.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipeKamar = sTipeKamar.getSelectedItem().toString();
                String nama = tNamaTamu.getText().toString();
                String nomor = tNomorTamu.getText().toString();
                String email = tEmailTamu.getText().toString();
                String checkIn = tTglCheckIn.getText().toString();
                String checkOut = tTglCheckIOut.getText().toString();
                String tipeTrip = sTipeTrip.getSelectedItem().toString();
                String Key = bookingKey.toString();
                if (pilih.equals("Ubah")){
                   databaseReference.child("Booking").child(key).setValue(new BookingModel(tipeKamar, nama, nomor, email, checkIn, checkOut,tipeTrip, Key)).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(view.getContext(), "Booking successfully updated!", Toast.LENGTH_SHORT).show();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(view.getContext(), "Booking failed to change!", Toast.LENGTH_SHORT).show();
                       }
                   });
                }

            }
        });




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog !=null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
