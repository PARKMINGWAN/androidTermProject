package com.example.dogwalker;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WalkerAdapter
        extends RecyclerView.Adapter<WalkerAdapter.ViewHolder> {
    private List<Walker> walkerList;
    public WalkerAdapter(List<Walker> walkerList){
        this.walkerList = walkerList;
    }
    private OnItemClickListener onItemClickListener;

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
        holder.txtName.setText("0");
        holder.txtId.setText("0");
        holder.txtPwd.setText("0");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = view.inflate(view.getContext(),
                        R.layout.item_basicinfo, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return walkerList == null ? 0 : walkerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtId, txtPwd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtId = itemView.findViewById(R.id.txtId);
            txtPwd = itemView.findViewById(R.id.txtPwd);
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
