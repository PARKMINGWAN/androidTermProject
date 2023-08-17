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
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WalkerMyPage extends AppCompatActivity {
    private List<Walker> walkerList;
    WalkerAdapter walkerAdapter;
    RecyclerView recyclerView1,recyclerView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_my_page);
        recyclerView1 = findViewById(R.id.recyclerView1);
       // recyclerView2 = findViewById(R.id.recyclerView2);
        Button btnInsert = findViewById(R.id.btnInsert);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false);
//        LinearLayoutManager linearLayoutManager1
//                = new LinearLayoutManager(this, RecyclerView.VERTICAL,
//                false);




        walkerList = new ArrayList<>();
        walkerAdapter = new WalkerAdapter(walkerList);

        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(walkerAdapter);
//        recyclerView2.setLayoutManager(linearLayoutManager1);
//        recyclerView2.setAdapter(null);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                View dialogView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.item_basicinfo, null);
                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etId = dialogView.findViewById(R.id.etId);
                EditText etPwd = dialogView.findViewById(R.id.etPwd);
                Log.d("BtnInsert click",dialogView.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(WalkerMyPage.this);
                builder.setView(dialogView);
                builder.setNegativeButton("취소",null);
                builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Walker walker = new Walker();
                        walker.setName("이름 : " + etName.getText().toString());
                        walker.setId("아이디 : "+LoginSharedPreferencesManager.getLoginInfo(WalkerMyPage.this).get("email"));
                        walker.setPwd("패스워드 : " + etPwd.getText().toString());
                        Log.d("insert",walker.getName().toString());
                        walkerAdapter.addItem(walker);
                        Log.d("아이디 저장", LoginSharedPreferencesManager.getLoginInfo(WalkerMyPage.this).get("email"));


                    }
                });
                builder.show();
            }
        });






    }
}