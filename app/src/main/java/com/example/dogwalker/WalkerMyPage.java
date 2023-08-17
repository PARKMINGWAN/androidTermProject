package com.example.dogwalker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class WalkerMyPage extends AppCompatActivity {
    private List<Walker> walkerList;
    WalkerAdapter walkerAdapter;
    RecyclerView recyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_my_page);
        recyclerView1 = findViewById(R.id.recyclerView1);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(WalkerMyPage.this, RecyclerView.VERTICAL,
                false);
        walkerList = new ArrayList<>();
        walkerAdapter = new WalkerAdapter(walkerList);
        recyclerView1.setAdapter(walkerAdapter);
        recyclerView1.setLayoutManager(linearLayoutManager);





    }
}