package com.example.dogwalker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity_tab extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Walker> walkerList;
    WalkerAdapter walkerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        TabLayout tabLayout = findViewById(R.id.layout_tab);
        ViewPager2 viewPager2 = findViewById(R.id.pager_content);

        ContentPagerAdapter contentPagerAdapter
                = new ContentPagerAdapter(this);
        viewPager2.setAdapter(contentPagerAdapter);

        List<String> tabElement = Arrays.asList("Home","Map","MyPage");

        //tabLayout viewPager2를 연결
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(MainActivity_tab.this);
                textView.setText(tabElement.get(position));
                tab.setCustomView(textView);

            }
        }).attach();





    }
}