package com.example.dogwalker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_tab extends TabActivity {
    RecyclerView recyclerView;
    private List<Walker> walkerList;
    WalkerAdapter walkerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        TabHost tabHost = getTabHost();
        Intent intent;
        TabHost.TabSpec spec;

        TabHost.TabSpec tabSpecSong = tabHost.newTabSpec("Home").setIndicator("Home");
        tabSpecSong.setContent(R.id.tabHome);
        tabHost.addTab(tabSpecSong);

        TabHost.TabSpec tabSpecArtist = tabHost.newTabSpec("Map").setIndicator("Map");
        tabSpecArtist.setContent(R.id.tabMap);
        tabHost.addTab(tabSpecArtist);

/*        TabHost.TabSpec tabSpecAlbum = tabHost.newTabSpec("MyPage").setIndicator("MyPage");
        tabSpecAlbum.setContent(R.id.tabMyPage);
        tabHost.addTab(tabSpecAlbum);  */

        //탭에서 액티비티를 사용할 수 있도록 인텐트 생성
        intent = new Intent().setClass(this, WalkerMyPage.class);
        spec = tabHost.newTabSpec("MyPage"); // 객체를 생성
        spec.setIndicator("MyPage"); //탭의 이름 설정
        spec.setContent(intent);
        tabHost.addTab(spec);




    }
}