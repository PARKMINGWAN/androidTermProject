package com.example.dogwalker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class OwnerListAdapter extends RecyclerView.Adapter<OwnerListAdapter.MyViewHolder> {
    DatabaseReference database;

    List<Owner> ownerList = new ArrayList<Owner>();

    @NonNull
    @Override
    public OwnerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.walkerlist_profile, parent, false);
        return new OwnerListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerListAdapter.MyViewHolder holder, int position) {
        Owner owner = ownerList.get(position);
        holder.setItem(owner);

    }

    public OwnerListAdapter(List<Owner> ownerList) {
        this.ownerList = ownerList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ownerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dogName, dogAge, dogBreed, dogWalk, addr;
        ImageView imgOwner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dogName = itemView.findViewById(R.id.dogName);
            dogAge = itemView.findViewById(R.id.dogAge);
            dogBreed = itemView.findViewById(R.id.dogBreed);
            dogWalk = itemView.findViewById(R.id.dogWalk);
            addr = itemView.findViewById(R.id.addr);
            imgOwner = itemView.findViewById(R.id.imgOwner);

        }

        public void setItem(Owner owner) {
            dogName.setText(owner.getName());
            dogAge.setText(owner.getDog_age());
            dogBreed.setText(owner.getBreed());
            dogWalk.setText(owner.getDog_walk());
            addr.setText(owner.getAddr());
            imgOwner.setImageResource(owner.getImg());

        }
    }
}