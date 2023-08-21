package com.example.dogwalker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class OwnerList extends AppCompatActivity {
    List<Owner> ownerList;

    private OwnerListAdapter ownerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ownerList = new ArrayList<Owner>();
        OwnerListAdapter ownerListAdapter = new OwnerListAdapter(ownerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( OwnerList.this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ownerListAdapter);

        for (int i = 0; i < ownerList.size(); i++) {
            if (i % 2 == 0) {

            }
        }

    }
}