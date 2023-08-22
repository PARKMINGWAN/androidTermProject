package com.example.dogwalker;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    Button btnImgInsert, btnUpdate, btnLogout,btnInsert;
    private static final  int GALLERY_CODE = 10 ;
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






    btnLogout=view.findViewById(R.id.btnLogout);
btnInsert=view.findViewById(R.id.btnInsert);
btnImgInsert=view.findViewById(R.id.btnimgInsert);
btnUpdate=view.findViewById(R.id.btnUpdate);


        txtName = view.findViewById(R.id.txtName);
        txtId = view.findViewById(R.id.txtId);
        txtPwd = view.findViewById(R.id.txtPwd);
        txtTel = view.findViewById(R.id.txtTel);
        txtAddr = view.findViewById(R.id.txtAddr);
        txtCareer = view.findViewById(R.id.txtCareer);
        txtNurture = view.findViewById(R.id.txtNurture);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();  //현재 로그인된 사용자
        uid = user.getUid();

   btnImgInsert.setOnClickListener(new View.OnClickListener() {


       @Override
       public void onClick(View view) {
           Intent intent = new Intent(Intent.ACTION_PICK);
           intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

           startActivityForResult(intent, GALLERY_CODE);
       }
   });


        readFirebaseValue(new FirebaseCallback1() {
            @Override
            public void onResponse(Walker value) {
                txtName.setText("이름 : "+value.getName());
                txtId.setText("아이디 : "+value.getId());
                txtAddr.setText("주소 : "+value.getAddr());
                txtTel.setText("전화번호 : "+value.getTel());
                txtPwd.setText("비밀번호 : "+value.getPwd());
                txtCareer.setText("산책경력 : "+value.getCareer());
                txtNurture.setText("양육 유무 : "+value.getNurture());
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> loginInfo = LoginSharedPreferencesManager.getLoginInfo(view.getContext());
                LoginSharedPreferencesManager.clearPreferences(view.getContext());
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_CODE)
        {//

        }
    }
    public String getPath(Uri uri)
    {
String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(getContext(),uri,proj,null,null,null);
        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToNext();

        return  cursor.getString(index);
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
                    //  Log.d(TAG, task.getException().getMessage());.
                }
            }
        });
    }
}