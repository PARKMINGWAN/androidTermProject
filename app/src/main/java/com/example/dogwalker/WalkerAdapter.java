package com.example.dogwalker;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WalkerAdapter
        extends RecyclerView.Adapter<WalkerAdapter.ViewHolder> {
    DatabaseReference mDatabase;
    private List<Walker> walkerList;
    public WalkerAdapter(List<Walker> walkerList){
        this.walkerList = walkerList;
    }
    private OnItemClickListener onItemClickListener;

   public void addItem(Walker walker){

       mDatabase = FirebaseDatabase.getInstance().getReference("users");

       mDatabase.child("Id").setValue(walker.getId().toString());

       mDatabase.child("Id").child("Name").setValue(walker.getName().toString());
       mDatabase.child("Id").child("PassWord").setValue(walker.getPwd().toString());
       mDatabase.child("Id").child("Tel").setValue(walker.getTel().toString());
       mDatabase.child("Id").child("Address").setValue(walker.getAddr().toString());
       mDatabase.child("Id").child("Career").setValue(walker.getCareer().toString());
       mDatabase.child("Id").child("Nurture").setValue(walker.getNurture().toString());

/*
       mDatabase.child(userId).setValue(walker.getName().toString());
       mDatabase.child(userId).setValue(walker.getAddr().toString());
       mDatabase.child(userId).setValue(walker.getTel().toString());
       mDatabase.child(userId).setValue(walker.getCareer().toString());
       mDatabase.child(userId).setValue(walker.getNurture().toString());*/
       walkerList.add(walker);

       notifyDataSetChanged();
   }


    public interface  OnItemClickListener{
        void onItemClick(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public WalkerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basic_info_list,parent,false);
        ViewHolder viewHoler = new ViewHolder(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull WalkerAdapter.ViewHolder holder,
                                 int position) {
        Walker walker = walkerList.get(position);

        holder.txtName.setText(walker.getName().toString());
        holder.txtId.setText(walker.getId().toString());
        holder.txtPwd.setText(walker.getPwd().toString());
        holder.txtTel.setText(walker.getTel().toString());
        holder.txtAddr.setText(walker.getAddr().toString());
        holder.txtCareer.setText(walker.getCareer().toString());
        holder.txtNurture.setText(walker.getNurture().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = view.inflate(view.getContext(),
                        R.layout.item_basicinfo, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etId = dialogView.findViewById(R.id.etId);
                EditText etPwd = dialogView.findViewById(R.id.etPwd);
                EditText etTel = dialogView.findViewById(R.id.etTel);
                EditText etAddr = dialogView.findViewById(R.id.etAddr);
                EditText etCareer = dialogView.findViewById(R.id.etCareer);
                EditText etNurture = dialogView.findViewById(R.id.etNurture);
                etName.setText(walker.getName());
                etId.setText(walker.getId());
                etPwd.setText(walker.getPwd());
                etTel.setText(walker.getTel());
                etAddr.setText(walker.getAddr());
                etCareer.setText(walker.getCareer());
                etNurture.setText(walker.getNurture());


                builder.setPositiveButton("수정.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        walker.setName(etName.getText().toString());
                        walker.setId(etId.getText().toString());
                        walker.setPwd(etPwd.getText().toString());
                        walker.setTel(etTel.getText().toString());
                        walker.setAddr(etAddr.getText().toString());
                        walker.setCareer( etCareer.getText().toString());
                        walker.setNurture( etNurture.getText().toString());
                        addItem(walker);

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("WalkerListSize",Integer.toString(walkerList.size()));
        return walkerList == null ? 0 : walkerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtId, txtPwd,txtTel,txtAddr,txtCareer,txtNurture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtId = itemView.findViewById(R.id.txtId);
            txtPwd = itemView.findViewById(R.id.txtPwd);
            txtTel = itemView.findViewById(R.id.txtTel);
            txtAddr = itemView.findViewById(R.id.txtAddr);
            txtCareer = itemView.findViewById(R.id.txtCareer);
            txtNurture = itemView.findViewById(R.id.txtNurture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    onItemClickListener.onItemClick(position);
                }
            });



        }
    }
}
