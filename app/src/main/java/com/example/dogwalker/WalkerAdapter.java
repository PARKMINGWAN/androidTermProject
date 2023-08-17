package com.example.dogwalker;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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
       mDatabase = FirebaseDatabase.getInstance().getReference();
       mDatabase.setValue(walker.getName().toString());

       //walkerList.add(walker);
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
        Log.d("walkerTxtName",holder.txtName.getText().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = view.inflate(view.getContext(),
                        R.layout.item_basicinfo, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setPositiveButton("수정.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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
        TextView txtName, txtId, txtPwd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtId = itemView.findViewById(R.id.txtId);
            txtPwd = itemView.findViewById(R.id.txtPwd);
            Log.d("txtName",txtName.getText().toString());
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
