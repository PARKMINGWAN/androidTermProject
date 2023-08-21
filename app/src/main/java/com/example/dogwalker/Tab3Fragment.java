package com.example.dogwalker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment {
    WalkerAdapter walkerAdapter;
    private List<Walker> walkerList;
    RecyclerView recyclerView1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3Fragment newInstance(String param1, String param2) {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tab3, container, false);
        Button btnInsert = view.findViewById(R.id.btnInsert);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        walkerList = new ArrayList<>();
        walkerAdapter = new WalkerAdapter(walkerList);
        recyclerView1 = view.findViewById(R.id.recyclerView1);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL,
                false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(walkerAdapter);



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> loginInfo = LoginSharedPreferencesManager.getLoginInfo(view.getContext());
                LoginSharedPreferencesManager.clearPreferences(view.getContext());
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.item_basicinfo, null);
                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etId = dialogView.findViewById(R.id.etId);
                EditText etPwd = dialogView.findViewById(R.id.etPwd);
                EditText etTel = dialogView.findViewById(R.id.etTel);
                EditText etAddr = dialogView.findViewById(R.id.etAddr);
                EditText etCareer = dialogView.findViewById(R.id.etCareer);
                EditText etNurture = dialogView.findViewById(R.id.etNurture);
                Log.d("BtnInsert click",dialogView.toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setNegativeButton("취소",null);
                etId.setText(LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("email"));
                etPwd.setText(LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("password"));
                builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Walker walker = new Walker();
                        walker.setName("이름 : " + etName.getText().toString());
                        walker.setId("아이디 : "+ LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("email"));
                        walker.setPwd("패스워드 : " + LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("password"));
                        walker.setTel("전화번호 : " + etTel.getText().toString());
                        walker.setAddr("주소 : " + etAddr.getText().toString());
                        walker.setCareer("산책 경력 : " + etCareer.getText().toString());
                        walker.setNurture("양육 유무 : " + etNurture.getText().toString());
                        walkerAdapter.addItem(walker);
                    }
                });
                builder.show();
            }
        });

        return view;
    }
}

