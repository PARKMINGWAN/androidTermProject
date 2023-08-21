package com.example.dogwalker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class OwnerList extends AppCompatActivity {
    List<Walker> walkerList;

    private OwnerListAdapter ownerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        walkerList = new ArrayList<>();
        WalkerAdapter walkerAdapter = new WalkerAdapter(walkerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( OwnerList.this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(walkerAdapter);

        for (int i = 0; i < walkerList.size(); i++) {
            if (i % 2 == 0) {
                walkerList.add(new Walker(

                ));
            }
        }

    }
}