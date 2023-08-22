package com.example.dogwalker;

import androidx.annotation.NonNull;
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
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OwnerList extends AppCompatActivity {
    List<Owner> ownerList;

    private OwnerListAdapter ownerListAdapter;
    EditText dog_name, dog_age, dog_breed, dog_walk, dog_addr;
    String id, name, pwd, tel, addr, breed, age, walk;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatBtn = findViewById(R.id.floatBtn);

        ownerList = new ArrayList<Owner>();
        ownerListAdapter = new OwnerListAdapter(ownerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( OwnerList.this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ownerListAdapter);




        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(getApplicationContext()).
                        inflate(R.layout.owner_list_add, null);
                dog_name = dialogView.findViewById(R.id.dog_name);
                dog_age = dialogView.findViewById(R.id.dog_age);
                dog_breed = dialogView.findViewById(R.id.dog_breed);
                dog_walk = dialogView.findViewById(R.id.dog_walk);
                dog_addr = dialogView.findViewById(R.id.dog_addr);

                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerList.this);
                builder.setTitle("반려인 프로필 등록");
                builder.setView(dialogView);
                builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Owner owner = new Owner();
                        owner.setName("이름 : " + dog_name.getText().toString());
                        owner.setDog_age("" + "(" + dog_age.getText().toString() + "살" + ")");
                        owner.setBreed("견종 : " + dog_breed.getText().toString());
                        owner.setDog_walk("산책 시간 : " + dog_walk.getText().toString() + "분");
                        owner.setAddr("주소 : " + dog_addr.getText().toString());

                        Log.d("onewr name : ", owner.getName().toString() );
                        Toast.makeText(getApplicationContext(),"addItem 진입",Toast.LENGTH_SHORT).show();
                        ownerListAdapter.addItem(owner);

                    }
                });
                builder.setNegativeButton("닫기", null);
                builder.show();

            }
        });

    }

}



