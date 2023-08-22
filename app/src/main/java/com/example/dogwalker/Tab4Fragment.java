package com.example.dogwalker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab4Fragment extends Fragment {
    private List<Owner> ownerList;
    OwnerAdapter ownerAdapter;
    Owner owner;
    DatabaseReference mDatabase;

    TextView txtId, txtPwd, txtTel, txtAddr, txtName, txtBreed, txtDogAge, txtDogWalk;
    EditText etId, etPwd, etTel, etAddr, etName, etBreed, etDogAge, etDogWalk;
    String uid;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab4Fragment newInstance(String param1, String param2) {
        Tab4Fragment fragment = new Tab4Fragment();
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
        View view = inflater.inflate(R.layout.fragment_tab4, container, false);

        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnInsert = view.findViewById(R.id.btnInsert);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);

        txtId = view.findViewById(R.id.txtId);
        txtPwd = view.findViewById(R.id.txtPwd);
        txtTel = view.findViewById(R.id.txtTel);
        txtAddr = view.findViewById(R.id.txtAddr);
        txtName = view.findViewById(R.id.txtName);
        txtBreed = view.findViewById(R.id.txtBreed);
        txtDogAge = view.findViewById(R.id.txtDogAge);
        txtDogWalk = view.findViewById(R.id.txtDogWalk);

        ownerList = new ArrayList<>();
        ownerAdapter = new OwnerAdapter(ownerList);

        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.item_basicinfo2, null);

        etId = dialogView.findViewById(R.id.etId);
        etPwd = dialogView.findViewById(R.id.etPwd);
        etTel = dialogView.findViewById(R.id.etTel);
        etAddr = dialogView.findViewById(R.id.etAddr);
        etName = dialogView.findViewById(R.id.etName);
        etBreed = dialogView.findViewById(R.id.etBreed);
        etDogAge = dialogView.findViewById(R.id.etDogAge);
        etDogWalk = dialogView.findViewById(R.id.etDogWalk);



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

        //추가
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference("users");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setNegativeButton("취소", null);

                etId.setText(LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("email"));
                etPwd.setText(LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("password"));

                builder.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        owner = new Owner();
                        owner.setId("아이디 : " + LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("email"));
                        owner.setPwd("패스워드 : " + LoginSharedPreferencesManager.getLoginInfo(view.getContext()).get("password"));
                        owner.setTel("전화번호 : " + etTel.getText().toString());
                        owner.setAddr("주소 : " + etAddr.getText().toString());
                        owner.setName("강아지 이름 : " + etName.getText().toString());
                        owner.setBreed("강아지 품종 : " + etBreed.getText().toString());
                        owner.setDog_age("강아지 나이 : " + etDogAge.getText().toString());
                        owner.setDog_walk("산책 시간 : " + etDogWalk.getText().toString());

                        mDatabase.child(uid).child("owner").setValue(owner);
                    }
                });
                builder.show();
            }
        });

        //수정
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);
                builder.setNegativeButton("취소", null);

                etId.setText(owner.getId());
                etPwd.setText(owner.getPwd());
                etTel.setText(owner.getTel());
                etAddr.setText(owner.getAddr());
                etName.setText(owner.getName());
                etBreed.setText(owner.getBreed());
                etDogAge.setText(owner.getDog_age());
                etDogWalk.setText(owner.getDog_walk());

                builder.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        return view;
    }

}