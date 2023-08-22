package com.example.dogwalker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class OwnerMyPage extends AppCompatActivity {
    private List<Owner> ownerList;
    OwnerAdapter ownerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_my_page);

        Button btnInsert = findViewById(R.id.btnInsert);

        ownerList = new ArrayList<>();
        ownerAdapter = new OwnerAdapter(ownerList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_basicinfo2, null);
                EditText etId = dialogView.findViewById(R.id.etId);
                EditText etPwd = dialogView.findViewById(R.id.etPwd);
                EditText etTel = dialogView.findViewById(R.id.etTel);
                EditText etAddr = dialogView.findViewById(R.id.etAddr);
                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etBreed = dialogView.findViewById(R.id.etBreed);
                EditText etDogAge = dialogView.findViewById(R.id.etDogAge);
                EditText etDogWalk = dialogView.findViewById(R.id.etDogWalk);
                Log.d("btnInsert click", dialogView.toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerMyPage.this);
                builder.setView(dialogView);

                etId.setText(LoginSharedPreferencesManager.getLoginInfo(OwnerMyPage.this).get("email"));
                etPwd.setText(LoginSharedPreferencesManager.getLoginInfo(OwnerMyPage.this).get("password"));
                builder.setNegativeButton("취소", null);
                builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Owner owner = new Owner();
                        owner.setId("아이디 : " + LoginSharedPreferencesManager.getLoginInfo(OwnerMyPage.this).get("email"));
                        owner.setPwd("패스워드 : " + LoginSharedPreferencesManager.getLoginInfo(OwnerMyPage.this).get("password"));
                        owner.setTel("전화번호 : " + etTel.getText().toString());
                        owner.setAddr("주소 : " + etAddr.getText().toString());
                        owner.setName("강아지 이름 : " + etName.getText().toString());
                        owner.setBreed("강아지 품종 : " + etBreed.getText().toString());
                        owner.setDog_age("강아지 나이 : " + etDogAge.getText().toString());
                        owner.setDog_walk("산책 시간 : " + etDogWalk.getText().toString());
                    }
                });
                builder.show();
            }
        });
    }
}