package com.medicine.doctorspatientportal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medicine.doctorspatientportal.AppointTakingActivity;
import com.medicine.doctorspatientportal.R;
import com.medicine.doctorspatientportal.model.Doctor;
import com.medicine.doctorspatientportal.model.User;
import com.medicine.doctorspatientportal.viewHolder.DoctorViewHolder;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorViewHolder> {

    List<Doctor> doctorList;
    Context context;

    public DoctorAdapter() {
    }

    public DoctorAdapter(List<Doctor> doctorList, Context context) {
        this.doctorList = doctorList;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.doctors_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor=doctorList.get(position);
        assert doctor!=null;
        holder.doctorName.setText(doctor.getFullName());
        holder.doctorCollege.setText(doctor.getCollege());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(doctor.getId());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Glide.with(context).load(user.getImgUrl()).placeholder(R.drawable.avatar).into(holder.doctorImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AppointTakingActivity.class);
                intent.putExtra("d_id", doctor.getId());
                context.startActivity(intent);
            }
        });

        if(doctor.getImage()==null){

        }
        else {
            Glide.with(holder.doctorImage).load(doctor.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }
}
