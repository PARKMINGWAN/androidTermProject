package com.example.dogwalker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class OwnerList extends AppCompatActivity {
    private OwnerListAdapter ownerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);
        setTitle("Puppy Owner");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatBtn = findViewById(R.id.floatBtn);

        List<Walker> walkerList = new ArrayList<>();
        ownerListAdapter = new OwnerListAdapter(walkerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OwnerList.this,
                RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ownerListAdapter);

        for (int i=0; i<walkerList.size(); i++) {
                walkerList.add(new Walker());
        }

        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });
    }

    private void addContact() {
        View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.owner_list_add, null);

        EditText name = dialogView.findViewById(R.id.name);
        EditText nurture = dialogView.findViewById(R.id.nurture);
        EditText carrer = dialogView.findViewById(R.id.carrer);

        AlertDialog.Builder builder = new AlertDialog.Builder(OwnerList.this);
        builder.setTitle("리스트 등록");
        builder.setView(dialogView);
        builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Walker walker = new Walker();
                walker.setName(name.getText().toString());
                walker.setNurture(nurture.getText().toString());
                walker.setCareer(carrer.getText().toString());
                Log.d("insert", "onClick: 등록 클릭시 값 확인" + walker);
                ownerListAdapter.addItem(walker);

            }
        });
        builder.setNegativeButton("닫기", null);
        builder.show();
    }

}