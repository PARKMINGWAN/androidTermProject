package com.example.dogwalker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnJoinPage;
    Button btnSignPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJoinPage =findViewById(R.id.btnJoinPage);
        btnSignPage =findViewById(R.id.btnSignInPage);

        //자동 로그인처리
        Map<String, String> loginInfo = LoginSharedPreferencesManager.getLoginInfo(this);
        if (!loginInfo.isEmpty()){
            String email    = loginInfo.get("email");
            String password = loginInfo.get("password");
        }


        btnJoinPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnSignPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}