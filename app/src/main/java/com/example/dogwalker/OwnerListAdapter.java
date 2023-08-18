package com.example.dogwalker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OwnerListAdapter extends RecyclerView.Adapter<OwnerListAdapter.MyViewHolder> {
    private List<Walker> walkerList;


    public OwnerListAdapter(List<Walker> walkerList) {
        this.walkerList = walkerList;
    }

    public void addItem(Walker walker) {
        walkerList.add(walker);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OwnerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ownerlist_profile, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerListAdapter.MyViewHolder holder, int position) {
        Walker walker = walkerList.get(position);
        holder.walkerName.setText(walker.getName());
        holder.walkerNurture.setText(walker.getNurture());
        holder.walkerCareer.setText(walker.getCareer());
        holder.imgWalker.setImageResource(R.drawable.walker);

    }

    @Override
    public int getItemCount() {
        return walkerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView walkerName, walkerNurture, walkerCareer;
        ImageView imgWalker;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            walkerName = itemView.findViewById(R.id.walkerName);
            walkerNurture = itemView.findViewById(R.id.walkerNurture);
            walkerCareer = itemView.findViewById(R.id.walkerCareer);
            imgWalker = itemView.findViewById(R.id.imgWalker);

        }
    }
}
