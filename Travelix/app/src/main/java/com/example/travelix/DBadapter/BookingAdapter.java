package com.example.travelix.DBadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelix.DialogFormBooking;
import com.example.travelix.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {
    private List<BookingModel> mList;
    private Activity activity;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public BookingAdapter(List<BookingModel>mList, Activity activity){
        this.mList = mList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View viewItem = inflate.inflate(R.layout.layout_booking, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final BookingModel data = mList.get(position);
        holder.tvGuestName.setText(data.getGuestName());
        holder.tvRoomType.setText(data.getRoomType());
        holder.tvCheckIn.setText(data.getCheckIn());
        holder.tvCheckOut.setText(data.getCheckOut());
        holder.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child("Booking").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(activity, "Booking has been canceled", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Failed to cancel booking, Please try again", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Are you sure want to cancel this Booking?");
                builder.show();
            }
        });

        holder.cardBooking.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogFormBooking dialog = new DialogFormBooking(
                        data.getRoomType(),
                        data.getGuestName(),
                        data.getGuestPhone(),
                        data.getGuestEmail(),
                        data.getCheckIn(),
                        data.getCheckOut(),
                        data.getTripType(),
                        data.getKey(),
                        "Ubah"
                );
                dialog.show(manager, "form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvGuestName, tvRoomType, tvCheckIn, tvCheckOut;
        CardView cardBooking;
        ImageView ivCancel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGuestName = itemView.findViewById(R.id.tv_guestName);
            tvRoomType = itemView.findViewById(R.id.tv_roomType);
            tvCheckIn = itemView.findViewById(R.id.tv_checkInDate);
            tvCheckOut = itemView.findViewById(R.id.tv_checkOutDate);
            cardBooking = itemView.findViewById(R.id.cv_hasil);
            ivCancel = itemView.findViewById(R.id.iv_cancel);

        }
    }
}
