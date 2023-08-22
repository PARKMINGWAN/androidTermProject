package com.example.dogwalker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OwnerListAdapter extends RecyclerView.Adapter<OwnerListAdapter.MyViewHolder> {
    DatabaseReference database;

    List<Owner> ownerList = new ArrayList<Owner>();

    public OwnerListAdapter(List<Owner> ownerList) {
        this.ownerList = ownerList;
        notifyDataSetChanged();
    }

    public interface onItemClickListener {
        void onItemClick(int pos);
    }
    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addItem(Owner owner) {
        //ㅁㄴToast.makeText(,Toast.LENGTH_SHORT).show();
        database =FirebaseDatabase.getInstance().getReference("users");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Log.d("uid check : ", uid);
       // Toast.makeText(context,"addItem 함수실행",Toast.LENGTH_SHORT).show();
        database.child(uid).child("Name").setValue(owner.getName().toString());
        database.child(uid).child("dog_Age").setValue(owner.getDog_age().toString());
        database.child(uid).child("Breed").setValue(owner.getBreed().toString());
        database.child(uid).child("dog_Walk").setValue(owner.getDog_walk().toString());
        database.child(uid).child("dog_Addr").setValue(owner.getAddr().toString());
        Log.d("onwer check : ", owner.getAddr().toString());
        //Toast.makeText(context,"addItem 함수",Toast.LENGTH_SHORT).show();
        ownerList.add(owner);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public OwnerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ownerlist_profile, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerListAdapter.MyViewHolder holder, int position) {
        Owner owner = ownerList.get(position);
        holder.dogName.setText(owner.getName().toString());
        holder.dogAge.setText(owner.getDog_age().toString());
        holder.dogBreed.setText(owner.getBreed().toString());
        holder.dogWalk.setText(owner.getDog_walk().toString());
        holder.addr.setText(owner.getAddr().toString());

    /*    holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dogName = holder.dogName.getText().toString();
                String dogAge = holder.dogAge.getText().toString();
                String dogBreed = holder.dogBreed.getText().toString();
                String dogWalk = holder.dogWalk.getText().toString();
                String addr = holder.addr.getText().toString();

                Intent intent;
                intent = new Intent(view.getContext(), OwnerList.class);
                view.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return ownerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    onItemClickListener.onItemClick(position);
                }
            });

        }

/*        void onBind(Owner owner) {
            dogName.setText(owner.getName());
            dogAge.setText(owner.getDog_age());
            dogBreed.setText(owner.getBreed());
            dogWalk.setText(owner.getDog_walk());
            addr.setText(owner.getAddr());
            imgOwner.setImageResource(owner.getImg());
        }*/
    }
}