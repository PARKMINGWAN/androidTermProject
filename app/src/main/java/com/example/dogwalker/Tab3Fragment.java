package com.example.dogwalker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public List<Walker> walkerList;
    Walker walker;
    DatabaseReference mDatabase;

    TextView txtName, txtId, txtPwd,txtTel,txtAddr,txtCareer,txtNurture;
    String name, id, pwd, tel, addr, career, nurture;

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
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        Button btnInsert = view.findViewById(R.id.btnInsert);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        txtName = view.findViewById(R.id.txtName);
        txtId = view.findViewById(R.id.txtId);
        txtPwd = view.findViewById(R.id.txtPwd);
        txtTel = view.findViewById(R.id.txtTel);
        txtAddr = view.findViewById(R.id.txtAddr);
        txtCareer = view.findViewById(R.id.txtCareer);
        txtNurture = view.findViewById(R.id.txtNurture);


        walkerList = new ArrayList<>();
        walkerAdapter = new WalkerAdapter(walkerList);

        View dialogView = LayoutInflater.from(view.getContext())
                .inflate(R.layout.item_basicinfo, null);
        EditText etName = dialogView.findViewById(R.id.etName);
        EditText etId = dialogView.findViewById(R.id.etId);
        EditText etPwd = dialogView.findViewById(R.id.etPwd);
        EditText etTel = dialogView.findViewById(R.id.etTel);
        EditText etAddr = dialogView.findViewById(R.id.etAddr);
        EditText etCareer = dialogView.findViewById(R.id.etCareer);
        EditText etNurture = dialogView.findViewById(R.id.etNurture);

        findAll();


        //로그아웃
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> loginInfo = LoginSharedPreferencesManager.getLoginInfo(view.getContext());
                LoginSharedPreferencesManager.clearPreferences(view.getContext());
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //수정
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setNegativeButton("취소", null);
                etId.setText(walker.getId());
                etName.setText(walker.getName());
                etPwd.setText(walker.getPwd());
                etTel.setText(walker.getTel());
                etAddr.setText(walker.getAddr());
                etCareer.setText(walker.getCareer());
                etNurture.setText(walker.getNurture());
                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        walker.setName(etName.getText().toString());
//                        walker.setId(etId.getText().toString());
//                        walker.setPwd(etPwd.getText().toString());
//                        walker.setTel(etTel.getText().toString());
//                        walker.setAddr(etAddr.getText().toString());
//                        walker.setCareer(etCareer.getText().toString());
//                        walker.setNurture(etNurture.getText().toString());
//
//                        txtId.setText(etId.getText().toString());
//                        txtName.setText(etId.getText().toString());
//                        txtPwd.setText(etId.getText().toString());
//                        txtAddr.setText(etId.getText().toString());
//                        txtTel.setText(etId.getText().toString());
//                        txtCareer.setText(etId.getText().toString());
//                        txtNurture.setText(etId.getText().toString());
//
//                        addItem(walker);
                    }
                });
                builder.show();

            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference("users");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();  //현재 로그인된 사용자
                String uid = user.getUid();

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setNegativeButton("취소", null);
                etId.setText(LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("email"));
                etPwd.setText(LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("password"));
                builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDatabase.child(uid).child("userId").setValue(etName.getText().toString());
                        mDatabase.child(uid).child("Name").setValue(etId.getText().toString());
                        mDatabase.child(uid).child("PassWord").setValue(etPwd.getText().toString());
                        mDatabase.child(uid).child("Address").setValue(etAddr.getText().toString());
                        mDatabase.child(uid).child("Tel").setValue(etTel.getText().toString());
                        mDatabase.child(uid).child("Career").setValue(etCareer.getText().toString());
                        mDatabase.child(uid).child("Nurture").setValue(etNurture.getText().toString());


                    }
                });
                builder.show();

            }
        });

        return view;
    }


    public void findAll() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();  //현재 로그인된 사용자
        String uid = user.getUid();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addr =  snapshot.child(uid).child("Address").getValue().toString();
                name =  snapshot.child(uid).child("Name").getValue().toString();
                id =  snapshot.child(uid).child("userId").getValue().toString();
                pwd =  snapshot.child(uid).child("PassWord").getValue().toString();
                tel =  snapshot.child(uid).child("Tel").getValue().toString();
                career =  snapshot.child(uid).child("Career").getValue().toString();
                nurture =  snapshot.child(uid).child("Nurture").getValue().toString();

                txtAddr.setText(addr);
                txtCareer.setText(career);
                txtName.setText(name);
                txtId.setText(id);
                txtNurture.setText(nurture);
                txtTel.setText(tel);
                txtPwd.setText(pwd);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}





