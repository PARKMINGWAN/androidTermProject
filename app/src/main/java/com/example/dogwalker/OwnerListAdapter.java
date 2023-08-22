package com.example.dogwalker;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OwnerListAdapter extends RecyclerView.Adapter<OwnerListAdapter.MyViewHolder> {
    DatabaseReference database;

    private OwnerListAdapter ownerListAdapter;

    private List<Owner> ownerList;

    OwnerListAdapter(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public interface onItemClickListener {
        void onItemClick(int pos);
    }
    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addItem(Owner owner) {
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

    //수정
    private void updateItem(Owner owner, int position) {
        Owner owner1 = ownerList.get(position);
        owner1.setName(owner.getName());
        owner1.setDog_age(owner.getDog_age());
        owner1.setBreed(owner.getBreed());
        owner1.setDog_walk(owner.getDog_walk());
        owner1.setAddr(owner.getAddr());
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = view.inflate(view.getContext(), R.layout.owner_list_add, null);
                EditText dog_name = dialogView.findViewById(R.id.dog_name);
                EditText dog_age = dialogView.findViewById(R.id.dog_age);
                EditText dog_breed = dialogView.findViewById(R.id.dog_breed);
                EditText dog_walk = dialogView.findViewById(R.id.dog_walk);
                EditText dog_addr = dialogView.findViewById(R.id.dog_addr);
                dog_name.setText(owner.getName());
                dog_age.setText(owner.getDog_age());
                dog_breed.setText(owner.getBreed());
                dog_walk.setText(owner.getDog_walk());
                dog_addr.setText(owner.getAddr());

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("반려인 프로필 수정");
                builder.setView(dialogView);

                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Owner owner1 = new Owner(
                                owner.getId(),
                                dog_name.getText().toString(),
                                dog_addr.getText().toString(),
                                dog_breed.getText().toString(),
                                dog_age.getText().toString(),
                                dog_walk.getText().toString());
                        //ownerListAdapter.updateItem(owner);

                    }
                });
                builder.setNegativeButton("닫기", null);
                builder.show();




                /*String dogName = holder.dogName.getText().toString();
                String dogAge = holder.dogAge.getText().toString();
                String dogBreed = holder.dogBreed.getText().toString();
                String dogWalk = holder.dogWalk.getText().toString();
                String addr = holder.addr.getText().toString();

                Intent intent;
                intent = new Intent(view.getContext(), OwnerList.class);
                view.getContext().startActivity(intent);*/
            }
        });
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