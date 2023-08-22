package com.example.dogwalker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseReference mDatabase;
    TextView txtName, txtId, txtPwd,txtTel,txtAddr,txtCareer,txtNurture;
    String uid;
    List<Owner> ownerList;

    private OwnerListAdapter ownerListAdapter;
    EditText dog_name, dog_age, dog_breed, dog_walk, dog_addr;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1Fragment newInstance(String param1, String param2) {
        Tab1Fragment fragment = new Tab1Fragment();
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

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton floatBtn = view.findViewById(R.id.floatBtn);

        ownerList = new ArrayList<Owner>();
        ownerListAdapter = new OwnerListAdapter(ownerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( view.getContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ownerListAdapter);

        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();  //현재 로그인된 사용자
        uid = user.getUid();


        readFirebaseValue(new FirebaseCallback1() {
            @Override
            public void onResponse(Walker value) {
//                txtName.setText("이름 : "+value.getName());
//                txtId.setText("아이디 : "+value.getId());
//                txtAddr.setText("주소 : "+value.getAddr());
//                txtTel.setText("전화번호 : "+value.getTel());
//                txtPwd.setText("비밀번호 : "+value.getPwd());
//                txtCareer.setText("산책경력 : "+value.getCareer());
//                txtNurture.setText("양육 유무 : "+value.getNurture());
            }
        });

        return view;
    }

    public void addContact() {
        View dialogView = LayoutInflater.from(getContext()).
                inflate(R.layout.owner_list_add, null);
        dog_name = dialogView.findViewById(R.id.dog_name);
        dog_age = dialogView.findViewById(R.id.dog_age);
        dog_breed = dialogView.findViewById(R.id.dog_breed);
        dog_walk = dialogView.findViewById(R.id.dog_walk);
        dog_addr = dialogView.findViewById(R.id.dog_addr);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                Toast.makeText(getContext(),"addItem 진입",Toast.LENGTH_SHORT).show();
                ownerListAdapter.addItem(owner);

            }
        });
        builder.setNegativeButton("닫기", null);
        builder.show();
    }

    public interface FirebaseCallback1 {
        void onResponse(Walker value);
    }



    public void readFirebaseValue(FirebaseCallback1 callback) {

        DatabaseReference uidRef = mDatabase.child(uid).child("walker");
        uidRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Walker value = task.getResult().getValue(Walker.class);

                    callback.onResponse(value);
                } else {
                    //  Log.d(TAG, task.getException().getMessage());
                }
            }
        });
    }
}