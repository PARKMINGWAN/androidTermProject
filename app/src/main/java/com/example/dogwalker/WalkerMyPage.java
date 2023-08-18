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
    RecyclerView recyclerView1;
    DatabaseReference mDatabase;
    private Walker walker;

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
        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.getDatabase();
*/
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                View dialogView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.item_basicinfo, null);
                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etId = dialogView.findViewById(R.id.etId);
                EditText etPwd = dialogView.findViewById(R.id.etPwd);
                EditText etTel = dialogView.findViewById(R.id.etTel);
                EditText etAddr = dialogView.findViewById(R.id.etAddr);
                EditText etCareer = dialogView.findViewById(R.id.etCareer);
                EditText etNurture = dialogView.findViewById(R.id.etNurture);
                Log.d("BtnInsert click",dialogView.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(WalkerMyPage.this);
                builder.setView(dialogView);
                etId.setText(LoginSharedPreferencesManager.getLoginInfo(WalkerMyPage.this).get("email"));
                etPwd.setText(LoginSharedPreferencesManager.getLoginInfo(WalkerMyPage.this).get("password"));
                builder.setNegativeButton("취소",null);
                builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Walker walker = new Walker();
                        walker.setName("이름 : " + etName.getText().toString());
                        walker.setId("아이디 : "+LoginSharedPreferencesManager.getLoginInfo(WalkerMyPage.this).get("email"));
                        walker.setPwd("패스워드 : " + LoginSharedPreferencesManager.getLoginInfo(WalkerMyPage.this).get("password"));
                        walker.setTel("전화번호 : " + etTel.getText().toString());
                        walker.setAddr("주소 : " + etAddr.getText().toString());
                        walker.setCareer("산책 경력 : " + etCareer.getText().toString());
                        walker.setNurture("양육 유무 : " + etNurture.getText().toString());
                        walkerAdapter.addItem(walker);
                        Log.d("아이디 저장", LoginSharedPreferencesManager.getLoginInfo(WalkerMyPage.this).get("email"));
                    }
                });
                builder.show();
            }
        });






    }
}