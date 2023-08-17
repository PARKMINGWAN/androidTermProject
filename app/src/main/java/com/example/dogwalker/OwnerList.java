package com.example.dogwalker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class OwnerList extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);
        setTitle("Puppy Owner");

        recyclerView = findViewById(R.id.recyclerView);

        OwnerListAdapter ownerListAdapter = new OwnerListAdapter();



    }
}